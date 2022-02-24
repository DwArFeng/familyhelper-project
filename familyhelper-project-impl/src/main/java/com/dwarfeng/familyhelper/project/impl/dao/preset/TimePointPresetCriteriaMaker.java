package com.dwarfeng.familyhelper.project.impl.dao.preset;

import com.dwarfeng.familyhelper.project.stack.service.TimePointMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class TimePointPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case TimePointMaintainService.CHILD_FOR_TASK:
                childForTask(detachedCriteria, objects);
                break;
            case TimePointMaintainService.CHILD_FOR_TASK_EXPECTED_FINISHED_DATE_ASC:
                childForTaskExpectedFinishedDateAsc(detachedCriteria, objects);
                break;
            case TimePointMaintainService.CHILD_FOR_TASK_EXPECTED_FINISHED_DATE_DESC:
                childForTaskExpectedFinishedDateDesc(detachedCriteria, objects);
                break;
            case TimePointMaintainService.CHILD_FOR_TASK_ACTUAL_FINISHED_DATE_ASC:
                childForTaskActualFinishedDateAsc(detachedCriteria, objects);
                break;
            case TimePointMaintainService.CHILD_FOR_TASK_ACTUAL_FINISHED_DATE_DESC:
                childForTaskActualFinishedDateDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForTask(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("taskLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("taskLongId", longIdKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForTaskExpectedFinishedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("taskLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("taskLongId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.asc("expectedFinishedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForTaskExpectedFinishedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("taskLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("taskLongId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.desc("expectedFinishedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForTaskActualFinishedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("taskLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("taskLongId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.asc("actualFinishedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForTaskActualFinishedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("taskLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("taskLongId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.desc("actualFinishedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
