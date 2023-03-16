package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.AccountPolicy;
import com.cloud.matrix.core.service.account.AccountPolicyService;
import com.cloud.matrix.dal.dao.account.AccountPolicyDAO;
import com.cloud.matrix.dal.model.account.AccountPolicyDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class AccountPolicyServiceImpl implements AccountPolicyService {

    @Resource
    private AccountPolicyDAO accountPolicyDAO;

    @Override
    public long add(AccountPolicy request) {
        return accountPolicyDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public AccountPolicy getUnique(String accountUid, String policyName, String providerUid) {
        return Convertor.INSTANCE.convert2Model(accountPolicyDAO.selectUnique(accountUid,
            policyName, providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<AccountPolicy> getByAccountUid(String accountUid, String providerUid) {
        List<AccountPolicyDO> dos = accountPolicyDAO.selectByAccountUid(accountUid, providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
