package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.AccountGroup;
import com.cloud.matrix.core.service.account.AccountGroupService;
import com.cloud.matrix.dal.dao.account.AccountGroupDAO;
import com.cloud.matrix.dal.model.account.AccountGroupDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class AccountGroupServiceImpl implements AccountGroupService {

    @Resource
    private AccountGroupDAO accountGroupDAO;

    @Override
    public long add(AccountGroup request) {
        return accountGroupDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public AccountGroup getUnique(String accountUid, String providerUid, String groupName) {
        return Convertor.INSTANCE.convert2Model(accountGroupDAO.selectUnique(accountUid, groupName,
            providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<AccountGroup> getByAccountUid(String accountUid, String providerUid) {
        List<AccountGroupDO> dos = accountGroupDAO.selectByAccountUid(accountUid, providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
