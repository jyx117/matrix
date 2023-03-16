package com.cloud.matrix.core.service.account.impl;

import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import com.cloud.matrix.core.model.account.AccountConfig;
import com.cloud.matrix.core.service.account.AccountConfigService;
import com.cloud.matrix.dal.dao.account.AccountConfigDAO;
import com.cloud.matrix.dal.model.account.AccountConfigDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class AccountConfigServiceImpl implements AccountConfigService {

    @Resource
    private AccountConfigDAO accountConfigDAO;

    @Override
    public long add(AccountConfig request) {
        return accountConfigDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public AccountConfig getUnique(String accountUid, String providerUid, String k) {
        return Convertor.INSTANCE.convert2Model(
            accountConfigDAO.selectUnique(accountUid, providerUid, k, CoreContext.getTenant()));
    }

    @Override
    public List<AccountConfig> getByAccountUid(String accountUid, String providerUid) {
        List<AccountConfigDO> dos = accountConfigDAO.selectByAccountUid(accountUid, providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
