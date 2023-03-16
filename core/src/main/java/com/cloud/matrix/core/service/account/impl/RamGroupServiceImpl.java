package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.RamGroup;
import com.cloud.matrix.core.service.account.RamGroupService;
import com.cloud.matrix.dal.dao.account.RamGroupDAO;
import com.cloud.matrix.dal.model.account.RamGroupDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: RamAccountServiceImpl.java, v 0.1 2023-03-16 6:34 PM Michael Exp $$
 */
@Service
public class RamGroupServiceImpl implements RamGroupService {

    @Resource
    private RamGroupDAO ramGroupDAO;

    @Override
    public long add(RamGroup request) {
        return ramGroupDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public RamGroup getByName(String name, String providerUid) {
        return Convertor.INSTANCE
            .convert2Model(ramGroupDAO.selectByName(name, providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<RamGroup> getByProviderUid(String providerUid) {
        List<RamGroupDO> dos = ramGroupDAO.selectByProviderUid(providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
