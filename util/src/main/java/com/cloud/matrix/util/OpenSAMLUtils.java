package com.cloud.matrix.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.x509.BasicX509Credential;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;
import java.util.UUID;

/**
 * @author michael
 * @version $Id: OpenSAMLUtils.java, v 0.1 2023-03-17 11:41 AM Michael Exp $$
 */
public class OpenSAMLUtils {

    // PKCS#8 format
    static final String                    PEM_PRIVATE_START     = "-----BEGIN PRIVATE KEY-----";
    static final String                    PEM_PRIVATE_END       = "-----END PRIVATE KEY-----";

    // PKCS#1 format
    static final String                    PEM_RSA_PRIVATE_START = "-----BEGIN RSA PRIVATE KEY-----";
    static final String                    PEM_RSA_PRIVATE_END   = "-----END RSA PRIVATE KEY-----";

    private static   XMLObjectBuilderFactory builderFactory = null;
    protected static MarshallerFactory       marshallerFactory;
    private static   VelocityEngine          velocityEngine = null;
    static {
        try {
            DefaultBootstrap.bootstrap(); // saml必需的初始化，否则后面构建对象会出现nullpointer
            builderFactory = Configuration.getBuilderFactory();
            marshallerFactory = Configuration.getMarshallerFactory();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static <T> T buildSAMLObject(final Class<T> clazz) {
        T object = null;
        try {
            QName defaultElementName = (QName) clazz.getDeclaredField("DEFAULT_ELEMENT_NAME")
                    .get(null);
            object = (T) builderFactory.getBuilder(defaultElementName)
                    .buildObject(defaultElementName);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not create SAML object");

        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Could not create SAML object");
        }

        return object;
    }

    public static MarshallerFactory getMarshallerFactory() {
        return marshallerFactory;
    }

    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }

    public static VelocityEngine getVelocityEngine() {
        if (velocityEngine == null) {
            velocityEngine = new VelocityEngine();
            velocityEngine.setProperty(RuntimeConstants.ENCODING_DEFAULT, "UTF-8");
            velocityEngine.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
            velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            velocityEngine.init();
        }
        return velocityEngine;
    }

    public static Credential getSigningCredential(String publicKeyStr,
                                                  String privateKeyStr) throws Exception {
        X509Certificate publicKey = parsePublicKey(publicKeyStr);

        PrivateKey privateKey = getPrivateKey(privateKeyStr);

        BasicX509Credential credential = new BasicX509Credential();
        credential.setEntityCertificate(publicKey);
        credential.setPrivateKey(privateKey);
        return credential;
    }

    public static X509Certificate parsePublicKey(String publicKeyStr) throws IOException,
            CertificateException {
        InputStream inStream = new ByteArrayInputStream(
                Base64.getMimeDecoder().decode(publicKeyStr));
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        try {
            return (X509Certificate) cf.generateCertificate(inStream);
        } finally {
            inStream.close();
        }
    }

    public static PrivateKey getPrivateKey(String privateKeyPem) throws GeneralSecurityException,
            IOException {
        if (privateKeyPem.indexOf(PEM_PRIVATE_START) != -1) { // PKCS#8 format
            privateKeyPem = privateKeyPem.replace(PEM_PRIVATE_START, "").replace(PEM_PRIVATE_END,
                    "");
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            byte[] pkcs8EncodedKey = Base64.getDecoder().decode(privateKeyPem);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));

        } else if (privateKeyPem.indexOf(PEM_RSA_PRIVATE_START) != -1) { // PKCS#1
            // format

            privateKeyPem = privateKeyPem.replace(PEM_RSA_PRIVATE_START, "")
                    .replace(PEM_RSA_PRIVATE_END, "");
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            DerInputStream derReader = new DerInputStream(
                    Base64.getDecoder().decode(privateKeyPem));

            DerValue[] seq = derReader.getSequence(0);

            if (seq.length < 9) {
                throw new GeneralSecurityException("Could not parse a PKCS1 private key.");
            }
            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger prime1 = seq[4].getBigInteger();
            BigInteger prime2 = seq[5].getBigInteger();
            BigInteger exp1 = seq[6].getBigInteger();
            BigInteger exp2 = seq[7].getBigInteger();
            BigInteger crtCoef = seq[8].getBigInteger();

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp,
                    prime1, prime2, exp1, exp2, crtCoef);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(keySpec);
        }
        throw new GeneralSecurityException("Not supported format of a private key");
    }
}
