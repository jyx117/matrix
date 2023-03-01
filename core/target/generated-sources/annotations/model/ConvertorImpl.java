package model;

import javax.annotation.processing.Generated;

import com.cloud.matrix.core.model.Convertor;
import com.cloud.matrix.model.UserDO;
import com.cloud.matrix.core.model.access.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-01T21:49:08+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class ConvertorImpl extends Convertor {

    @Override
    public User convert2Do(UserDO userDO) {
        if ( userDO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDO.getId() );
        user.setGmtCreate( userDO.getGmtCreate() );
        user.setGmtModified( userDO.getGmtModified() );
        user.setName( userDO.getName() );

        return user;
    }

    @Override
    public UserDO convert2Do(User user) {
        if ( user == null ) {
            return null;
        }

        UserDO userDO = new UserDO();

        userDO.setId( user.getId() );
        userDO.setGmtCreate( user.getGmtCreate() );
        userDO.setGmtModified( user.getGmtModified() );
        userDO.setName( user.getName() );

        return userDO;
    }
}
