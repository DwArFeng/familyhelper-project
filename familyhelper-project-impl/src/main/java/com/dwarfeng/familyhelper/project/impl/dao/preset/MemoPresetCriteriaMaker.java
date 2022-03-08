package com.dwarfeng.familyhelper.project.impl.dao.preset;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class MemoPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case MemoMaintainService.CHILD_FOR_USER:
                childForUser(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_IN_PROGRESS:
                childForUserNotFinished(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_CREATED_DATE_DESC:
                childForUserCreatedDateDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForUserNotFinished(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_IN_PROGRESS));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForUserCreatedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
