package com.cloud.matrix.core.service.account.impl;

import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import com.cloud.matrix.core.model.account.RamAccount;
import com.cloud.matrix.core.service.account.RamAccountService;
import com.cloud.matrix.dal.dao.account.RamAccountDAO;
import com.cloud.matrix.dal.model.account.RamAccountDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author michael
 * @version $Id: RamAccountServiceImpl.java, v 0.1 2023-03-16 6:34 PM Michael Exp $$
 */
@Service
public class RamAccountServiceImpl implements RamAccountService {

    @Resource
    private RamAccountDAO ramAccountDAO;

    @Override
    public long add(RamAccount request) {
        return ramAccountDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public RamAccount getByLoginName(String loginName, String providerUid) {
        return Convertor.INSTANCE.convert2Model(
            ramAccountDAO.selectByLoginName(loginName, providerUid, CoreContext.getTenant()));
    }

    @Override
    public RamAccount getByName(String name, String providerUid) {
        return Convertor.INSTANCE
            .convert2Model(ramAccountDAO.selectByName(name, providerUid, CoreContext.getTenant()));
    }

    @Override
    public RamAccount getByUid(String uid, String providerUid) {
        return Convertor.INSTANCE
            .convert2Model(ramAccountDAO.selectByUid(uid, providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<RamAccount> getByProviderUid(String providerUid) {
        return convertToList(ramAccountDAO.selectByProviderUid(providerUid,
            CoreContext.getTenant()));
    }

    @Override
    public List<RamAccount> getByUidListAndProviderUid(List uidList, String providerUid) {
        return convertToList(ramAccountDAO.selectByUidListAndProviderUid(uidList, providerUid,
                CoreContext.getTenant()));
    }

    private List<RamAccount> convertToList(List<RamAccountDO> dos) {
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
