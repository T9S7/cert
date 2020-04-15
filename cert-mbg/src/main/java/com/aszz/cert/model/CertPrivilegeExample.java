package com.aszz.cert.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertPrivilegeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CertPrivilegeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterIsNull() {
            addCriterion("privilege_master is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterIsNotNull() {
            addCriterion("privilege_master is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterEqualTo(Integer value) {
            addCriterion("privilege_master =", value, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterNotEqualTo(Integer value) {
            addCriterion("privilege_master <>", value, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterGreaterThan(Integer value) {
            addCriterion("privilege_master >", value, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterGreaterThanOrEqualTo(Integer value) {
            addCriterion("privilege_master >=", value, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterLessThan(Integer value) {
            addCriterion("privilege_master <", value, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterLessThanOrEqualTo(Integer value) {
            addCriterion("privilege_master <=", value, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterIn(List<Integer> values) {
            addCriterion("privilege_master in", values, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterNotIn(List<Integer> values) {
            addCriterion("privilege_master not in", values, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterBetween(Integer value1, Integer value2) {
            addCriterion("privilege_master between", value1, value2, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterNotBetween(Integer value1, Integer value2) {
            addCriterion("privilege_master not between", value1, value2, "privilegeMaster");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueIsNull() {
            addCriterion("privilege_master_value is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueIsNotNull() {
            addCriterion("privilege_master_value is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueEqualTo(Long value) {
            addCriterion("privilege_master_value =", value, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueNotEqualTo(Long value) {
            addCriterion("privilege_master_value <>", value, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueGreaterThan(Long value) {
            addCriterion("privilege_master_value >", value, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueGreaterThanOrEqualTo(Long value) {
            addCriterion("privilege_master_value >=", value, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueLessThan(Long value) {
            addCriterion("privilege_master_value <", value, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueLessThanOrEqualTo(Long value) {
            addCriterion("privilege_master_value <=", value, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueIn(List<Long> values) {
            addCriterion("privilege_master_value in", values, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueNotIn(List<Long> values) {
            addCriterion("privilege_master_value not in", values, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueBetween(Long value1, Long value2) {
            addCriterion("privilege_master_value between", value1, value2, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeMasterValueNotBetween(Long value1, Long value2) {
            addCriterion("privilege_master_value not between", value1, value2, "privilegeMasterValue");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeIsNull() {
            addCriterion("privilege_resource_type is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeIsNotNull() {
            addCriterion("privilege_resource_type is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeEqualTo(Integer value) {
            addCriterion("privilege_resource_type =", value, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeNotEqualTo(Integer value) {
            addCriterion("privilege_resource_type <>", value, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeGreaterThan(Integer value) {
            addCriterion("privilege_resource_type >", value, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("privilege_resource_type >=", value, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeLessThan(Integer value) {
            addCriterion("privilege_resource_type <", value, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("privilege_resource_type <=", value, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeIn(List<Integer> values) {
            addCriterion("privilege_resource_type in", values, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeNotIn(List<Integer> values) {
            addCriterion("privilege_resource_type not in", values, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeBetween(Integer value1, Integer value2) {
            addCriterion("privilege_resource_type between", value1, value2, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("privilege_resource_type not between", value1, value2, "privilegeResourceType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdIsNull() {
            addCriterion("privilege_resource_id is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdIsNotNull() {
            addCriterion("privilege_resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdEqualTo(Long value) {
            addCriterion("privilege_resource_id =", value, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdNotEqualTo(Long value) {
            addCriterion("privilege_resource_id <>", value, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdGreaterThan(Long value) {
            addCriterion("privilege_resource_id >", value, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("privilege_resource_id >=", value, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdLessThan(Long value) {
            addCriterion("privilege_resource_id <", value, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdLessThanOrEqualTo(Long value) {
            addCriterion("privilege_resource_id <=", value, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdIn(List<Long> values) {
            addCriterion("privilege_resource_id in", values, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdNotIn(List<Long> values) {
            addCriterion("privilege_resource_id not in", values, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdBetween(Long value1, Long value2) {
            addCriterion("privilege_resource_id between", value1, value2, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeResourceIdNotBetween(Long value1, Long value2) {
            addCriterion("privilege_resource_id not between", value1, value2, "privilegeResourceId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationIsNull() {
            addCriterion("privilege_operation is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationIsNotNull() {
            addCriterion("privilege_operation is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationEqualTo(Integer value) {
            addCriterion("privilege_operation =", value, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationNotEqualTo(Integer value) {
            addCriterion("privilege_operation <>", value, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationGreaterThan(Integer value) {
            addCriterion("privilege_operation >", value, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationGreaterThanOrEqualTo(Integer value) {
            addCriterion("privilege_operation >=", value, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationLessThan(Integer value) {
            addCriterion("privilege_operation <", value, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationLessThanOrEqualTo(Integer value) {
            addCriterion("privilege_operation <=", value, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationIn(List<Integer> values) {
            addCriterion("privilege_operation in", values, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationNotIn(List<Integer> values) {
            addCriterion("privilege_operation not in", values, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationBetween(Integer value1, Integer value2) {
            addCriterion("privilege_operation between", value1, value2, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andPrivilegeOperationNotBetween(Integer value1, Integer value2) {
            addCriterion("privilege_operation not between", value1, value2, "privilegeOperation");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Long value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Long value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Long value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Long value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Long value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Long> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Long> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Long value1, Long value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Long value1, Long value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Long value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Long value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Long value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Long value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Long value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Long> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Long> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Long value1, Long value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Long value1, Long value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}