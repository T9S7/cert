package com.aszz.cert.model;

import java.util.ArrayList;
import java.util.List;

public class OperateLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperateLogExample() {
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

        public Criteria andOpDescIsNull() {
            addCriterion("op_desc is null");
            return (Criteria) this;
        }

        public Criteria andOpDescIsNotNull() {
            addCriterion("op_desc is not null");
            return (Criteria) this;
        }

        public Criteria andOpDescEqualTo(String value) {
            addCriterion("op_desc =", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotEqualTo(String value) {
            addCriterion("op_desc <>", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescGreaterThan(String value) {
            addCriterion("op_desc >", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescGreaterThanOrEqualTo(String value) {
            addCriterion("op_desc >=", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescLessThan(String value) {
            addCriterion("op_desc <", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescLessThanOrEqualTo(String value) {
            addCriterion("op_desc <=", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescLike(String value) {
            addCriterion("op_desc like", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotLike(String value) {
            addCriterion("op_desc not like", value, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescIn(List<String> values) {
            addCriterion("op_desc in", values, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotIn(List<String> values) {
            addCriterion("op_desc not in", values, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescBetween(String value1, String value2) {
            addCriterion("op_desc between", value1, value2, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpDescNotBetween(String value1, String value2) {
            addCriterion("op_desc not between", value1, value2, "opDesc");
            return (Criteria) this;
        }

        public Criteria andOpUserIsNull() {
            addCriterion("op_user is null");
            return (Criteria) this;
        }

        public Criteria andOpUserIsNotNull() {
            addCriterion("op_user is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserEqualTo(String value) {
            addCriterion("op_user =", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotEqualTo(String value) {
            addCriterion("op_user <>", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserGreaterThan(String value) {
            addCriterion("op_user >", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserGreaterThanOrEqualTo(String value) {
            addCriterion("op_user >=", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLessThan(String value) {
            addCriterion("op_user <", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLessThanOrEqualTo(String value) {
            addCriterion("op_user <=", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLike(String value) {
            addCriterion("op_user like", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotLike(String value) {
            addCriterion("op_user not like", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserIn(List<String> values) {
            addCriterion("op_user in", values, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotIn(List<String> values) {
            addCriterion("op_user not in", values, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserBetween(String value1, String value2) {
            addCriterion("op_user between", value1, value2, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotBetween(String value1, String value2) {
            addCriterion("op_user not between", value1, value2, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeIsNull() {
            addCriterion("op_start_time is null");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeIsNotNull() {
            addCriterion("op_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeEqualTo(Long value) {
            addCriterion("op_start_time =", value, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeNotEqualTo(Long value) {
            addCriterion("op_start_time <>", value, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeGreaterThan(Long value) {
            addCriterion("op_start_time >", value, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("op_start_time >=", value, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeLessThan(Long value) {
            addCriterion("op_start_time <", value, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("op_start_time <=", value, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeIn(List<Long> values) {
            addCriterion("op_start_time in", values, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeNotIn(List<Long> values) {
            addCriterion("op_start_time not in", values, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeBetween(Long value1, Long value2) {
            addCriterion("op_start_time between", value1, value2, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("op_start_time not between", value1, value2, "opStartTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeIsNull() {
            addCriterion("op_spend_time is null");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeIsNotNull() {
            addCriterion("op_spend_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeEqualTo(Long value) {
            addCriterion("op_spend_time =", value, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeNotEqualTo(Long value) {
            addCriterion("op_spend_time <>", value, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeGreaterThan(Long value) {
            addCriterion("op_spend_time >", value, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("op_spend_time >=", value, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeLessThan(Long value) {
            addCriterion("op_spend_time <", value, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeLessThanOrEqualTo(Long value) {
            addCriterion("op_spend_time <=", value, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeIn(List<Long> values) {
            addCriterion("op_spend_time in", values, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeNotIn(List<Long> values) {
            addCriterion("op_spend_time not in", values, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeBetween(Long value1, Long value2) {
            addCriterion("op_spend_time between", value1, value2, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpSpendTimeNotBetween(Long value1, Long value2) {
            addCriterion("op_spend_time not between", value1, value2, "opSpendTime");
            return (Criteria) this;
        }

        public Criteria andOpBasePathIsNull() {
            addCriterion("op_base_path is null");
            return (Criteria) this;
        }

        public Criteria andOpBasePathIsNotNull() {
            addCriterion("op_base_path is not null");
            return (Criteria) this;
        }

        public Criteria andOpBasePathEqualTo(String value) {
            addCriterion("op_base_path =", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathNotEqualTo(String value) {
            addCriterion("op_base_path <>", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathGreaterThan(String value) {
            addCriterion("op_base_path >", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathGreaterThanOrEqualTo(String value) {
            addCriterion("op_base_path >=", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathLessThan(String value) {
            addCriterion("op_base_path <", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathLessThanOrEqualTo(String value) {
            addCriterion("op_base_path <=", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathLike(String value) {
            addCriterion("op_base_path like", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathNotLike(String value) {
            addCriterion("op_base_path not like", value, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathIn(List<String> values) {
            addCriterion("op_base_path in", values, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathNotIn(List<String> values) {
            addCriterion("op_base_path not in", values, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathBetween(String value1, String value2) {
            addCriterion("op_base_path between", value1, value2, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpBasePathNotBetween(String value1, String value2) {
            addCriterion("op_base_path not between", value1, value2, "opBasePath");
            return (Criteria) this;
        }

        public Criteria andOpUriIsNull() {
            addCriterion("op_uri is null");
            return (Criteria) this;
        }

        public Criteria andOpUriIsNotNull() {
            addCriterion("op_uri is not null");
            return (Criteria) this;
        }

        public Criteria andOpUriEqualTo(String value) {
            addCriterion("op_uri =", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriNotEqualTo(String value) {
            addCriterion("op_uri <>", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriGreaterThan(String value) {
            addCriterion("op_uri >", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriGreaterThanOrEqualTo(String value) {
            addCriterion("op_uri >=", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriLessThan(String value) {
            addCriterion("op_uri <", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriLessThanOrEqualTo(String value) {
            addCriterion("op_uri <=", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriLike(String value) {
            addCriterion("op_uri like", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriNotLike(String value) {
            addCriterion("op_uri not like", value, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriIn(List<String> values) {
            addCriterion("op_uri in", values, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriNotIn(List<String> values) {
            addCriterion("op_uri not in", values, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriBetween(String value1, String value2) {
            addCriterion("op_uri between", value1, value2, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUriNotBetween(String value1, String value2) {
            addCriterion("op_uri not between", value1, value2, "opUri");
            return (Criteria) this;
        }

        public Criteria andOpUrlIsNull() {
            addCriterion("op_url is null");
            return (Criteria) this;
        }

        public Criteria andOpUrlIsNotNull() {
            addCriterion("op_url is not null");
            return (Criteria) this;
        }

        public Criteria andOpUrlEqualTo(String value) {
            addCriterion("op_url =", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotEqualTo(String value) {
            addCriterion("op_url <>", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlGreaterThan(String value) {
            addCriterion("op_url >", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlGreaterThanOrEqualTo(String value) {
            addCriterion("op_url >=", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlLessThan(String value) {
            addCriterion("op_url <", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlLessThanOrEqualTo(String value) {
            addCriterion("op_url <=", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlLike(String value) {
            addCriterion("op_url like", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotLike(String value) {
            addCriterion("op_url not like", value, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlIn(List<String> values) {
            addCriterion("op_url in", values, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotIn(List<String> values) {
            addCriterion("op_url not in", values, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlBetween(String value1, String value2) {
            addCriterion("op_url between", value1, value2, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpUrlNotBetween(String value1, String value2) {
            addCriterion("op_url not between", value1, value2, "opUrl");
            return (Criteria) this;
        }

        public Criteria andOpIpIsNull() {
            addCriterion("op_ip is null");
            return (Criteria) this;
        }

        public Criteria andOpIpIsNotNull() {
            addCriterion("op_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOpIpEqualTo(String value) {
            addCriterion("op_ip =", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpNotEqualTo(String value) {
            addCriterion("op_ip <>", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpGreaterThan(String value) {
            addCriterion("op_ip >", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpGreaterThanOrEqualTo(String value) {
            addCriterion("op_ip >=", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpLessThan(String value) {
            addCriterion("op_ip <", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpLessThanOrEqualTo(String value) {
            addCriterion("op_ip <=", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpLike(String value) {
            addCriterion("op_ip like", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpNotLike(String value) {
            addCriterion("op_ip not like", value, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpIn(List<String> values) {
            addCriterion("op_ip in", values, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpNotIn(List<String> values) {
            addCriterion("op_ip not in", values, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpBetween(String value1, String value2) {
            addCriterion("op_ip between", value1, value2, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpIpNotBetween(String value1, String value2) {
            addCriterion("op_ip not between", value1, value2, "opIp");
            return (Criteria) this;
        }

        public Criteria andOpMethodIsNull() {
            addCriterion("op_method is null");
            return (Criteria) this;
        }

        public Criteria andOpMethodIsNotNull() {
            addCriterion("op_method is not null");
            return (Criteria) this;
        }

        public Criteria andOpMethodEqualTo(String value) {
            addCriterion("op_method =", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodNotEqualTo(String value) {
            addCriterion("op_method <>", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodGreaterThan(String value) {
            addCriterion("op_method >", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodGreaterThanOrEqualTo(String value) {
            addCriterion("op_method >=", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodLessThan(String value) {
            addCriterion("op_method <", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodLessThanOrEqualTo(String value) {
            addCriterion("op_method <=", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodLike(String value) {
            addCriterion("op_method like", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodNotLike(String value) {
            addCriterion("op_method not like", value, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodIn(List<String> values) {
            addCriterion("op_method in", values, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodNotIn(List<String> values) {
            addCriterion("op_method not in", values, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodBetween(String value1, String value2) {
            addCriterion("op_method between", value1, value2, "opMethod");
            return (Criteria) this;
        }

        public Criteria andOpMethodNotBetween(String value1, String value2) {
            addCriterion("op_method not between", value1, value2, "opMethod");
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