package com.huishoucat.manager.pojo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWasteOrderItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWasteOrderItemExample() {
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

        public Criteria andOrderItemIdIsNull() {
            addCriterion("order_item_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNotNull() {
            addCriterion("order_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdEqualTo(Long value) {
            addCriterion("order_item_id =", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotEqualTo(Long value) {
            addCriterion("order_item_id <>", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThan(Long value) {
            addCriterion("order_item_id >", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_item_id >=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThan(Long value) {
            addCriterion("order_item_id <", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThanOrEqualTo(Long value) {
            addCriterion("order_item_id <=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIn(List<Long> values) {
            addCriterion("order_item_id in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotIn(List<Long> values) {
            addCriterion("order_item_id not in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdBetween(Long value1, Long value2) {
            addCriterion("order_item_id between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotBetween(Long value1, Long value2) {
            addCriterion("order_item_id not between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdIsNull() {
            addCriterion("waste_order_id is null");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdIsNotNull() {
            addCriterion("waste_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdEqualTo(Long value) {
            addCriterion("waste_order_id =", value, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdNotEqualTo(Long value) {
            addCriterion("waste_order_id <>", value, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdGreaterThan(Long value) {
            addCriterion("waste_order_id >", value, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("waste_order_id >=", value, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdLessThan(Long value) {
            addCriterion("waste_order_id <", value, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("waste_order_id <=", value, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdIn(List<Long> values) {
            addCriterion("waste_order_id in", values, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdNotIn(List<Long> values) {
            addCriterion("waste_order_id not in", values, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdBetween(Long value1, Long value2) {
            addCriterion("waste_order_id between", value1, value2, "wasteOrderId");
            return (Criteria) this;
        }

        public Criteria andWasteOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("waste_order_id not between", value1, value2, "wasteOrderId");
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andUnitPointIsNull() {
            addCriterion("unit_point is null");
            return (Criteria) this;
        }

        public Criteria andUnitPointIsNotNull() {
            addCriterion("unit_point is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPointEqualTo(Long value) {
            addCriterion("unit_point =", value, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointNotEqualTo(Long value) {
            addCriterion("unit_point <>", value, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointGreaterThan(Long value) {
            addCriterion("unit_point >", value, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointGreaterThanOrEqualTo(Long value) {
            addCriterion("unit_point >=", value, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointLessThan(Long value) {
            addCriterion("unit_point <", value, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointLessThanOrEqualTo(Long value) {
            addCriterion("unit_point <=", value, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointIn(List<Long> values) {
            addCriterion("unit_point in", values, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointNotIn(List<Long> values) {
            addCriterion("unit_point not in", values, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointBetween(Long value1, Long value2) {
            addCriterion("unit_point between", value1, value2, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andUnitPointNotBetween(Long value1, Long value2) {
            addCriterion("unit_point not between", value1, value2, "unitPoint");
            return (Criteria) this;
        }

        public Criteria andAttributePriceIsNull() {
            addCriterion("attribute_price is null");
            return (Criteria) this;
        }

        public Criteria andAttributePriceIsNotNull() {
            addCriterion("attribute_price is not null");
            return (Criteria) this;
        }

        public Criteria andAttributePriceEqualTo(Long value) {
            addCriterion("attribute_price =", value, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceNotEqualTo(Long value) {
            addCriterion("attribute_price <>", value, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceGreaterThan(Long value) {
            addCriterion("attribute_price >", value, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("attribute_price >=", value, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceLessThan(Long value) {
            addCriterion("attribute_price <", value, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceLessThanOrEqualTo(Long value) {
            addCriterion("attribute_price <=", value, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceIn(List<Long> values) {
            addCriterion("attribute_price in", values, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceNotIn(List<Long> values) {
            addCriterion("attribute_price not in", values, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceBetween(Long value1, Long value2) {
            addCriterion("attribute_price between", value1, value2, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andAttributePriceNotBetween(Long value1, Long value2) {
            addCriterion("attribute_price not between", value1, value2, "attributePrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceIsNull() {
            addCriterion("estimate_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceIsNotNull() {
            addCriterion("estimate_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceEqualTo(Long value) {
            addCriterion("estimate_unit_price =", value, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceNotEqualTo(Long value) {
            addCriterion("estimate_unit_price <>", value, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceGreaterThan(Long value) {
            addCriterion("estimate_unit_price >", value, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("estimate_unit_price >=", value, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceLessThan(Long value) {
            addCriterion("estimate_unit_price <", value, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceLessThanOrEqualTo(Long value) {
            addCriterion("estimate_unit_price <=", value, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceIn(List<Long> values) {
            addCriterion("estimate_unit_price in", values, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceNotIn(List<Long> values) {
            addCriterion("estimate_unit_price not in", values, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceBetween(Long value1, Long value2) {
            addCriterion("estimate_unit_price between", value1, value2, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateUnitPriceNotBetween(Long value1, Long value2) {
            addCriterion("estimate_unit_price not between", value1, value2, "estimateUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceIsNull() {
            addCriterion("real_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceIsNotNull() {
            addCriterion("real_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceEqualTo(Long value) {
            addCriterion("real_unit_price =", value, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceNotEqualTo(Long value) {
            addCriterion("real_unit_price <>", value, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceGreaterThan(Long value) {
            addCriterion("real_unit_price >", value, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("real_unit_price >=", value, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceLessThan(Long value) {
            addCriterion("real_unit_price <", value, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceLessThanOrEqualTo(Long value) {
            addCriterion("real_unit_price <=", value, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceIn(List<Long> values) {
            addCriterion("real_unit_price in", values, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceNotIn(List<Long> values) {
            addCriterion("real_unit_price not in", values, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceBetween(Long value1, Long value2) {
            addCriterion("real_unit_price between", value1, value2, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andRealUnitPriceNotBetween(Long value1, Long value2) {
            addCriterion("real_unit_price not between", value1, value2, "realUnitPrice");
            return (Criteria) this;
        }

        public Criteria andEstimateNumIsNull() {
            addCriterion("estimate_num is null");
            return (Criteria) this;
        }

        public Criteria andEstimateNumIsNotNull() {
            addCriterion("estimate_num is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateNumEqualTo(Integer value) {
            addCriterion("estimate_num =", value, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumNotEqualTo(Integer value) {
            addCriterion("estimate_num <>", value, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumGreaterThan(Integer value) {
            addCriterion("estimate_num >", value, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("estimate_num >=", value, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumLessThan(Integer value) {
            addCriterion("estimate_num <", value, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumLessThanOrEqualTo(Integer value) {
            addCriterion("estimate_num <=", value, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumIn(List<Integer> values) {
            addCriterion("estimate_num in", values, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumNotIn(List<Integer> values) {
            addCriterion("estimate_num not in", values, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumBetween(Integer value1, Integer value2) {
            addCriterion("estimate_num between", value1, value2, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andEstimateNumNotBetween(Integer value1, Integer value2) {
            addCriterion("estimate_num not between", value1, value2, "estimateNum");
            return (Criteria) this;
        }

        public Criteria andRealNumIsNull() {
            addCriterion("real_num is null");
            return (Criteria) this;
        }

        public Criteria andRealNumIsNotNull() {
            addCriterion("real_num is not null");
            return (Criteria) this;
        }

        public Criteria andRealNumEqualTo(Integer value) {
            addCriterion("real_num =", value, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumNotEqualTo(Integer value) {
            addCriterion("real_num <>", value, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumGreaterThan(Integer value) {
            addCriterion("real_num >", value, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_num >=", value, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumLessThan(Integer value) {
            addCriterion("real_num <", value, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumLessThanOrEqualTo(Integer value) {
            addCriterion("real_num <=", value, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumIn(List<Integer> values) {
            addCriterion("real_num in", values, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumNotIn(List<Integer> values) {
            addCriterion("real_num not in", values, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumBetween(Integer value1, Integer value2) {
            addCriterion("real_num between", value1, value2, "realNum");
            return (Criteria) this;
        }

        public Criteria andRealNumNotBetween(Integer value1, Integer value2) {
            addCriterion("real_num not between", value1, value2, "realNum");
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