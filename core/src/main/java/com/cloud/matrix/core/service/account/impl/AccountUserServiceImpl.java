package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.AccountUser;
import com.cloud.matrix.core.service.account.AccountUserService;
import com.cloud.matrix.dal.dao.account.AccountUserDAO;
import com.cloud.matrix.dal.model.account.AccountUserDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {

    @Resource
    private AccountUserDAO accountUserDAO;

    @Override
    public long add(AccountUser request) {
        return accountUserDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public AccountUser getUnique(String userId, String accountUid, String providerUid) {
        return Convertor.INSTANCE.convert2Model(
            accountUserDAO.selectUnique(userId, accountUid, providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<AccountUser> getByUserIdAndProviderUid(String userId, String providerUid) {
        List<AccountUserDO> dos = accountUserDAO.selectByUserIdAndProviderUid(userId, providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
