package com.cloud.matrix.core.service.account.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return convertList(accountUserDAO.selectByUserIdAndProviderUid(userId, providerUid,
            CoreContext.getTenant()));
    }

    @Override
    public List<AccountUser> getByUserId(String userId, int pageNum, int pageSize) {
        return convertList(
            accountUserDAO.selectByUserId(userId, CoreContext.getTenant(), pageNum, pageSize));
    }

    @Override
    public List<AccountUser> getByUserIdAndType(String userId, String type, int pageNum,
                                                int pageSize) {
        return convertList(accountUserDAO.selectByUserIdAndType(userId, type,
            CoreContext.getTenant(), pageNum, pageSize));
    }

    @Override
    public List<AccountUser> getByCondition(Map param) {
        if (null == param) {
            param = new HashMap();
        }
        param.put("tenant", CoreContext.getTenant());
        return convertList(accountUserDAO.selectByCondition(param));
    }

    @Override
    public long getCountByCondition(Map param) {
        if (null == param) {
            param = new HashMap();
        }
        param.put("tenant", CoreContext.getTenant());
        return accountUserDAO.selectCountByCondition(param);
    }

    @Override
    public List<AccountUser> getByUidListAndType(List uidList, String type) {
        return convertList(accountUserDAO.selectByUidListAndType(uidList, type, CoreContext.getTenant()));
    }

    private List<AccountUser> convertList(List<AccountUserDO> dos) {
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
