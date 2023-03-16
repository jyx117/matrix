package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.GroupPolicy;
import com.cloud.matrix.core.service.account.GroupPolicyService;
import com.cloud.matrix.dal.dao.account.GroupPolicyDAO;
import com.cloud.matrix.dal.model.account.GroupPolicyDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class GroupPolicyServiceImpl implements GroupPolicyService {

    @Resource
    private GroupPolicyDAO groupPolicyDAO;

    @Override
    public long add(GroupPolicy request) {
        return groupPolicyDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public GroupPolicy getUnique(String groupName, String policyName, String providerUid) {
        return Convertor.INSTANCE.convert2Model(groupPolicyDAO.selectUnique(groupName, policyName,
            providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<GroupPolicy> getByGroupName(String groupName, String providerUid) {
        List<GroupPolicyDO> dos = groupPolicyDAO.selectByGroupName(groupName, providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
