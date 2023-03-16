package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.RamRole;
import com.cloud.matrix.core.service.account.RamRoleService;
import com.cloud.matrix.dal.dao.account.RamRoleDAO;
import com.cloud.matrix.dal.model.account.RamRoleDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: RamAccountServiceImpl.java, v 0.1 2023-03-16 6:34 PM Michael Exp $$
 */
@Service
public class RamRoleServiceImpl implements RamRoleService {

    @Resource
    private RamRoleDAO ramRoleDAO;

    @Override
    public long add(RamRole request) {
        return ramRoleDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public RamRole getByName(String name, String providerUid) {
        return Convertor.INSTANCE
            .convert2Model(ramRoleDAO.selectByName(name, providerUid, CoreContext.getTenant()));
    }

    @Override
    public RamRole getByArn(String arn, String providerUid) {
        return Convertor.INSTANCE
            .convert2Model(ramRoleDAO.selectByArn(arn, providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<RamRole> getByProviderUid(String providerUid) {
        List<RamRoleDO> dos = ramRoleDAO.selectByProviderUid(providerUid, CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
