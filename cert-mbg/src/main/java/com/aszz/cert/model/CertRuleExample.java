package com.aszz.cert.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CertRuleExample() {
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

        public Criteria andCertRuleNameIsNull() {
            addCriterion("cert_rule_name is null");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameIsNotNull() {
            addCriterion("cert_rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameEqualTo(String value) {
            addCriterion("cert_rule_name =", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameNotEqualTo(String value) {
            addCriterion("cert_rule_name <>", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameGreaterThan(String value) {
            addCriterion("cert_rule_name >", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("cert_rule_name >=", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameLessThan(String value) {
            addCriterion("cert_rule_name <", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameLessThanOrEqualTo(String value) {
            addCriterion("cert_rule_name <=", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameLike(String value) {
            addCriterion("cert_rule_name like", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameNotLike(String value) {
            addCriterion("cert_rule_name not like", value, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameIn(List<String> values) {
            addCriterion("cert_rule_name in", values, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameNotIn(List<String> values) {
            addCriterion("cert_rule_name not in", values, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameBetween(String value1, String value2) {
            addCriterion("cert_rule_name between", value1, value2, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleNameNotBetween(String value1, String value2) {
            addCriterion("cert_rule_name not between", value1, value2, "certRuleName");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescIsNull() {
            addCriterion("cert_rule_desc is null");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescIsNotNull() {
            addCriterion("cert_rule_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescEqualTo(String value) {
            addCriterion("cert_rule_desc =", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescNotEqualTo(String value) {
            addCriterion("cert_rule_desc <>", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescGreaterThan(String value) {
            addCriterion("cert_rule_desc >", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescGreaterThanOrEqualTo(String value) {
            addCriterion("cert_rule_desc >=", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescLessThan(String value) {
            addCriterion("cert_rule_desc <", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescLessThanOrEqualTo(String value) {
            addCriterion("cert_rule_desc <=", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescLike(String value) {
            addCriterion("cert_rule_desc like", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescNotLike(String value) {
            addCriterion("cert_rule_desc not like", value, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescIn(List<String> values) {
            addCriterion("cert_rule_desc in", values, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescNotIn(List<String> values) {
            addCriterion("cert_rule_desc not in", values, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescBetween(String value1, String value2) {
            addCriterion("cert_rule_desc between", value1, value2, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleDescNotBetween(String value1, String value2) {
            addCriterion("cert_rule_desc not between", value1, value2, "certRuleDesc");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusIsNull() {
            addCriterion("cert_rule_status is null");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusIsNotNull() {
            addCriterion("cert_rule_status is not null");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusEqualTo(Integer value) {
            addCriterion("cert_rule_status =", value, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusNotEqualTo(Integer value) {
            addCriterion("cert_rule_status <>", value, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusGreaterThan(Integer value) {
            addCriterion("cert_rule_status >", value, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("cert_rule_status >=", value, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusLessThan(Integer value) {
            addCriterion("cert_rule_status <", value, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("cert_rule_status <=", value, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusIn(List<Integer> values) {
            addCriterion("cert_rule_status in", values, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusNotIn(List<Integer> values) {
            addCriterion("cert_rule_status not in", values, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusBetween(Integer value1, Integer value2) {
            addCriterion("cert_rule_status between", value1, value2, "certRuleStatus");
            return (Criteria) this;
        }

        public Criteria andCertRuleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("cert_rule_status not between", value1, value2, "certRuleStatus");
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