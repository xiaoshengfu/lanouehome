package com.huishoucat.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWastePriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWastePriceExample() {
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

        public Criteria andWastePriceIdIsNull() {
            addCriterion("waste_price_id is null");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdIsNotNull() {
            addCriterion("waste_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdEqualTo(Long value) {
            addCriterion("waste_price_id =", value, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdNotEqualTo(Long value) {
            addCriterion("waste_price_id <>", value, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdGreaterThan(Long value) {
            addCriterion("waste_price_id >", value, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("waste_price_id >=", value, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdLessThan(Long value) {
            addCriterion("waste_price_id <", value, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdLessThanOrEqualTo(Long value) {
            addCriterion("waste_price_id <=", value, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdIn(List<Long> values) {
            addCriterion("waste_price_id in", values, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdNotIn(List<Long> values) {
            addCriterion("waste_price_id not in", values, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdBetween(Long value1, Long value2) {
            addCriterion("waste_price_id between", value1, value2, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWastePriceIdNotBetween(Long value1, Long value2) {
            addCriterion("waste_price_id not between", value1, value2, "wastePriceId");
            return (Criteria) this;
        }

        public Criteria andWasteIdIsNull() {
            addCriterion("waste_id is null");
            return (Criteria) this;
        }

        public Criteria andWasteIdIsNotNull() {
            addCriterion("waste_id is not null");
            return (Criteria) this;
        }

        public Criteria andWasteIdEqualTo(Long value) {
            addCriterion("waste_id =", value, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdNotEqualTo(Long value) {
            addCriterion("waste_id <>", value, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdGreaterThan(Long value) {
            addCriterion("waste_id >", value, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("waste_id >=", value, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdLessThan(Long value) {
            addCriterion("waste_id <", value, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdLessThanOrEqualTo(Long value) {
            addCriterion("waste_id <=", value, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdIn(List<Long> values) {
            addCriterion("waste_id in", values, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdNotIn(List<Long> values) {
            addCriterion("waste_id not in", values, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdBetween(Long value1, Long value2) {
            addCriterion("waste_id between", value1, value2, "wasteId");
            return (Criteria) this;
        }

        public Criteria andWasteIdNotBetween(Long value1, Long value2) {
            addCriterion("waste_id not between", value1, value2, "wasteId");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingIsNull() {
            addCriterion("price_ceiling is null");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingIsNotNull() {
            addCriterion("price_ceiling is not null");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingEqualTo(Long value) {
            addCriterion("price_ceiling =", value, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingNotEqualTo(Long value) {
            addCriterion("price_ceiling <>", value, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingGreaterThan(Long value) {
            addCriterion("price_ceiling >", value, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingGreaterThanOrEqualTo(Long value) {
            addCriterion("price_ceiling >=", value, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingLessThan(Long value) {
            addCriterion("price_ceiling <", value, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingLessThanOrEqualTo(Long value) {
            addCriterion("price_ceiling <=", value, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingIn(List<Long> values) {
            addCriterion("price_ceiling in", values, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingNotIn(List<Long> values) {
            addCriterion("price_ceiling not in", values, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingBetween(Long value1, Long value2) {
            addCriterion("price_ceiling between", value1, value2, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceCeilingNotBetween(Long value1, Long value2) {
            addCriterion("price_ceiling not between", value1, value2, "priceCeiling");
            return (Criteria) this;
        }

        public Criteria andPriceFloorIsNull() {
            addCriterion("price_floor is null");
            return (Criteria) this;
        }

        public Criteria andPriceFloorIsNotNull() {
            addCriterion("price_floor is not null");
            return (Criteria) this;
        }

        public Criteria andPriceFloorEqualTo(Long value) {
            addCriterion("price_floor =", value, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorNotEqualTo(Long value) {
            addCriterion("price_floor <>", value, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorGreaterThan(Long value) {
            addCriterion("price_floor >", value, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorGreaterThanOrEqualTo(Long value) {
            addCriterion("price_floor >=", value, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorLessThan(Long value) {
            addCriterion("price_floor <", value, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorLessThanOrEqualTo(Long value) {
            addCriterion("price_floor <=", value, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorIn(List<Long> values) {
            addCriterion("price_floor in", values, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorNotIn(List<Long> values) {
            addCriterion("price_floor not in", values, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorBetween(Long value1, Long value2) {
            addCriterion("price_floor between", value1, value2, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andPriceFloorNotBetween(Long value1, Long value2) {
            addCriterion("price_floor not between", value1, value2, "priceFloor");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(Integer value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(Integer value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(Integer value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(Integer value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(Integer value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<Integer> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<Integer> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(Integer value1, Integer value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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