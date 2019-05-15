package com.zjh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AffixExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AffixExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAffixnameIsNull() {
            addCriterion("affixname is null");
            return (Criteria) this;
        }

        public Criteria andAffixnameIsNotNull() {
            addCriterion("affixname is not null");
            return (Criteria) this;
        }

        public Criteria andAffixnameEqualTo(String value) {
            addCriterion("affixname =", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameNotEqualTo(String value) {
            addCriterion("affixname <>", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameGreaterThan(String value) {
            addCriterion("affixname >", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameGreaterThanOrEqualTo(String value) {
            addCriterion("affixname >=", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameLessThan(String value) {
            addCriterion("affixname <", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameLessThanOrEqualTo(String value) {
            addCriterion("affixname <=", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameLike(String value) {
            addCriterion("affixname like", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameNotLike(String value) {
            addCriterion("affixname not like", value, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameIn(List<String> values) {
            addCriterion("affixname in", values, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameNotIn(List<String> values) {
            addCriterion("affixname not in", values, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameBetween(String value1, String value2) {
            addCriterion("affixname between", value1, value2, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixnameNotBetween(String value1, String value2) {
            addCriterion("affixname not between", value1, value2, "affixname");
            return (Criteria) this;
        }

        public Criteria andAffixtypeIsNull() {
            addCriterion("affixtype is null");
            return (Criteria) this;
        }

        public Criteria andAffixtypeIsNotNull() {
            addCriterion("affixtype is not null");
            return (Criteria) this;
        }

        public Criteria andAffixtypeEqualTo(String value) {
            addCriterion("affixtype =", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeNotEqualTo(String value) {
            addCriterion("affixtype <>", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeGreaterThan(String value) {
            addCriterion("affixtype >", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeGreaterThanOrEqualTo(String value) {
            addCriterion("affixtype >=", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeLessThan(String value) {
            addCriterion("affixtype <", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeLessThanOrEqualTo(String value) {
            addCriterion("affixtype <=", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeLike(String value) {
            addCriterion("affixtype like", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeNotLike(String value) {
            addCriterion("affixtype not like", value, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeIn(List<String> values) {
            addCriterion("affixtype in", values, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeNotIn(List<String> values) {
            addCriterion("affixtype not in", values, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeBetween(String value1, String value2) {
            addCriterion("affixtype between", value1, value2, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtypeNotBetween(String value1, String value2) {
            addCriterion("affixtype not between", value1, value2, "affixtype");
            return (Criteria) this;
        }

        public Criteria andAffixtimeIsNull() {
            addCriterion("affixtime is null");
            return (Criteria) this;
        }

        public Criteria andAffixtimeIsNotNull() {
            addCriterion("affixtime is not null");
            return (Criteria) this;
        }

        public Criteria andAffixtimeEqualTo(Date value) {
            addCriterion("affixtime =", value, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeNotEqualTo(Date value) {
            addCriterion("affixtime <>", value, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeGreaterThan(Date value) {
            addCriterion("affixtime >", value, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("affixtime >=", value, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeLessThan(Date value) {
            addCriterion("affixtime <", value, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeLessThanOrEqualTo(Date value) {
            addCriterion("affixtime <=", value, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeIn(List<Date> values) {
            addCriterion("affixtime in", values, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeNotIn(List<Date> values) {
            addCriterion("affixtime not in", values, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeBetween(Date value1, Date value2) {
            addCriterion("affixtime between", value1, value2, "affixtime");
            return (Criteria) this;
        }

        public Criteria andAffixtimeNotBetween(Date value1, Date value2) {
            addCriterion("affixtime not between", value1, value2, "affixtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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