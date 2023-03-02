package com.cloud.matrix.core.service;

import com.cloud.matrix.core.model.Tenant;

/**
 * @author michael
 * @version $Id: TenantService.java, v 0.1 2023-03-02 4:29 PM Michael Exp $$
 */
public interface TenantService {

    public Tenant getByCode(String code);

    public long add(Tenant request);
}
