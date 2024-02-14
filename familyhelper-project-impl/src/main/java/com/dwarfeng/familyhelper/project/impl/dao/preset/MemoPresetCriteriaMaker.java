package com.dwarfeng.familyhelper.project.impl.dao.preset;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
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
                childForUserInProgress(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_FINISHED:
                childForUserFinished(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_CREATED_DATE_DESC:
                childForUserCreatedDateDesc(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_IN_PROGRESS_CREATED_DATE_DESC:
                childForUserInProgressCreatedDateDesc(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_FINISHED_CREATED_DATE_DESC:
                childForUserFinishedCreatedDateDesc(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_DEFAULT_ORDER:
                childForUserDefaultOrder(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_IN_PROGRESS_DEFAULT_ORDER:
                childForUserInProgressDefaultOrder(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_FINISHED_DEFAULT_ORDER:
                childForUserFinishedDefaultOrder(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_PROFILE_LIKE_DEFAULT_ORDER:
                childForUserProfileLikeDefaultOrder(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_IN_PROGRESS_PROFILE_LIKE_DEFAULT_ORDER:
                childForUserInProgressProfileLikeDefaultOrder(detachedCriteria, objects);
                break;
            case MemoMaintainService.CHILD_FOR_USER_FINISHED_PROFILE_LIKE_DEFAULT_ORDER:
                childForUserFinishedProfileLikeDefaultOrder(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
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

    @SuppressWarnings("DuplicatedCode")
    private void childForUserInProgress(DetachedCriteria detachedCriteria, Object[] objects) {
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

    @SuppressWarnings("DuplicatedCode")
    private void childForUserFinished(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_FINISHED));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
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

    @SuppressWarnings("DuplicatedCode")
    private void childForUserInProgressCreatedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_IN_PROGRESS));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserFinishedCreatedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_FINISHED));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserDefaultOrder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.addOrder(Order.desc("starFlag"));
            detachedCriteria.addOrder(Order.desc("priority"));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserInProgressDefaultOrder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_IN_PROGRESS));
            detachedCriteria.addOrder(Order.desc("starFlag"));
            detachedCriteria.addOrder(Order.desc("priority"));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserFinishedDefaultOrder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_FINISHED));
            detachedCriteria.addOrder(Order.desc("starFlag"));
            detachedCriteria.addOrder(Order.desc("priority"));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserProfileLikeDefaultOrder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            // 条件。
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            String profilePattern = (String) objects[1];
            detachedCriteria.add(Restrictions.like("profile", profilePattern, MatchMode.ANYWHERE));
            // 顺序。
            detachedCriteria.addOrder(Order.desc("starFlag"));
            detachedCriteria.addOrder(Order.desc("priority"));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserInProgressProfileLikeDefaultOrder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            // 条件。
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            String profilePattern = (String) objects[1];
            detachedCriteria.add(Restrictions.like("profile", profilePattern, MatchMode.ANYWHERE));
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_IN_PROGRESS));
            // 顺序。
            detachedCriteria.addOrder(Order.desc("starFlag"));
            detachedCriteria.addOrder(Order.desc("priority"));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUserFinishedProfileLikeDefaultOrder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            // 条件。
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            String profilePattern = (String) objects[1];
            detachedCriteria.add(Restrictions.like("profile", profilePattern, MatchMode.ANYWHERE));
            detachedCriteria.add(Restrictions.eq("status", Constants.MEMO_STATUS_FINISHED));
            // 顺序。
            detachedCriteria.addOrder(Order.desc("starFlag"));
            detachedCriteria.addOrder(Order.desc("priority"));
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
