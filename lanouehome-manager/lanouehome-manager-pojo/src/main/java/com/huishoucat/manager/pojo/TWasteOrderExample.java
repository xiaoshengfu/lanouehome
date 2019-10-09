package com.huishoucat.manager.pojo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWasteOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWasteOrderExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdIsNull() {
            addCriterion("recycler_id is null");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdIsNotNull() {
            addCriterion("recycler_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdEqualTo(Long value) {
            addCriterion("recycler_id =", value, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdNotEqualTo(Long value) {
            addCriterion("recycler_id <>", value, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdGreaterThan(Long value) {
            addCriterion("recycler_id >", value, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recycler_id >=", value, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdLessThan(Long value) {
            addCriterion("recycler_id <", value, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdLessThanOrEqualTo(Long value) {
            addCriterion("recycler_id <=", value, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdIn(List<Long> values) {
            addCriterion("recycler_id in", values, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdNotIn(List<Long> values) {
            addCriterion("recycler_id not in", values, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdBetween(Long value1, Long value2) {
            addCriterion("recycler_id between", value1, value2, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andRecyclerIdNotBetween(Long value1, Long value2) {
            addCriterion("recycler_id not between", value1, value2, "recyclerId");
            return (Criteria) this;
        }

        public Criteria andReserveTimeIsNull() {
            addCriterion("reserve_time is null");
            return (Criteria) this;
        }

        public Criteria andReserveTimeIsNotNull() {
            addCriterion("reserve_time is not null");
            return (Criteria) this;
        }

        public Criteria andReserveTimeEqualTo(Date value) {
            addCriterion("reserve_time =", value, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeNotEqualTo(Date value) {
            addCriterion("reserve_time <>", value, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeGreaterThan(Date value) {
            addCriterion("reserve_time >", value, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reserve_time >=", value, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeLessThan(Date value) {
            addCriterion("reserve_time <", value, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeLessThanOrEqualTo(Date value) {
            addCriterion("reserve_time <=", value, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeIn(List<Date> values) {
            addCriterion("reserve_time in", values, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeNotIn(List<Date> values) {
            addCriterion("reserve_time not in", values, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeBetween(Date value1, Date value2) {
            addCriterion("reserve_time between", value1, value2, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andReserveTimeNotBetween(Date value1, Date value2) {
            addCriterion("reserve_time not between", value1, value2, "reserveTime");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdIsNull() {
            addCriterion("user_address_id is null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdIsNotNull() {
            addCriterion("user_address_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdEqualTo(Long value) {
            addCriterion("user_address_id =", value, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdNotEqualTo(Long value) {
            addCriterion("user_address_id <>", value, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdGreaterThan(Long value) {
            addCriterion("user_address_id >", value, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_address_id >=", value, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdLessThan(Long value) {
            addCriterion("user_address_id <", value, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdLessThanOrEqualTo(Long value) {
            addCriterion("user_address_id <=", value, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdIn(List<Long> values) {
            addCriterion("user_address_id in", values, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdNotIn(List<Long> values) {
            addCriterion("user_address_id not in", values, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdBetween(Long value1, Long value2) {
            addCriterion("user_address_id between", value1, value2, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIdNotBetween(Long value1, Long value2) {
            addCriterion("user_address_id not between", value1, value2, "userAddressId");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceIsNull() {
            addCriterion("estimate_price is null");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceIsNotNull() {
            addCriterion("estimate_price is not null");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceEqualTo(Long value) {
            addCriterion("estimate_price =", value, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceNotEqualTo(Long value) {
            addCriterion("estimate_price <>", value, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceGreaterThan(Long value) {
            addCriterion("estimate_price >", value, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("estimate_price >=", value, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceLessThan(Long value) {
            addCriterion("estimate_price <", value, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceLessThanOrEqualTo(Long value) {
            addCriterion("estimate_price <=", value, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceIn(List<Long> values) {
            addCriterion("estimate_price in", values, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceNotIn(List<Long> values) {
            addCriterion("estimate_price not in", values, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceBetween(Long value1, Long value2) {
            addCriterion("estimate_price between", value1, value2, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePriceNotBetween(Long value1, Long value2) {
            addCriterion("estimate_price not between", value1, value2, "estimatePrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNull() {
            addCriterion("real_price is null");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNotNull() {
            addCriterion("real_price is not null");
            return (Criteria) this;
        }

        public Criteria andRealPriceEqualTo(Long value) {
            addCriterion("real_price =", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotEqualTo(Long value) {
            addCriterion("real_price <>", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThan(Long value) {
            addCriterion("real_price >", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("real_price >=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThan(Long value) {
            addCriterion("real_price <", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThanOrEqualTo(Long value) {
            addCriterion("real_price <=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIn(List<Long> values) {
            addCriterion("real_price in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotIn(List<Long> values) {
            addCriterion("real_price not in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceBetween(Long value1, Long value2) {
            addCriterion("real_price between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotBetween(Long value1, Long value2) {
            addCriterion("real_price not between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andEstimatePointIsNull() {
            addCriterion("estimate_point is null");
            return (Criteria) this;
        }

        public Criteria andEstimatePointIsNotNull() {
            addCriterion("estimate_point is not null");
            return (Criteria) this;
        }

        public Criteria andEstimatePointEqualTo(Long value) {
            addCriterion("estimate_point =", value, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointNotEqualTo(Long value) {
            addCriterion("estimate_point <>", value, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointGreaterThan(Long value) {
            addCriterion("estimate_point >", value, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointGreaterThanOrEqualTo(Long value) {
            addCriterion("estimate_point >=", value, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointLessThan(Long value) {
            addCriterion("estimate_point <", value, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointLessThanOrEqualTo(Long value) {
            addCriterion("estimate_point <=", value, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointIn(List<Long> values) {
            addCriterion("estimate_point in", values, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointNotIn(List<Long> values) {
            addCriterion("estimate_point not in", values, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointBetween(Long value1, Long value2) {
            addCriterion("estimate_point between", value1, value2, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andEstimatePointNotBetween(Long value1, Long value2) {
            addCriterion("estimate_point not between", value1, value2, "estimatePoint");
            return (Criteria) this;
        }

        public Criteria andRealPointIsNull() {
            addCriterion("real_point is null");
            return (Criteria) this;
        }

        public Criteria andRealPointIsNotNull() {
            addCriterion("real_point is not null");
            return (Criteria) this;
        }

        public Criteria andRealPointEqualTo(Long value) {
            addCriterion("real_point =", value, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointNotEqualTo(Long value) {
            addCriterion("real_point <>", value, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointGreaterThan(Long value) {
            addCriterion("real_point >", value, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointGreaterThanOrEqualTo(Long value) {
            addCriterion("real_point >=", value, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointLessThan(Long value) {
            addCriterion("real_point <", value, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointLessThanOrEqualTo(Long value) {
            addCriterion("real_point <=", value, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointIn(List<Long> values) {
            addCriterion("real_point in", values, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointNotIn(List<Long> values) {
            addCriterion("real_point not in", values, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointBetween(Long value1, Long value2) {
            addCriterion("real_point between", value1, value2, "realPoint");
            return (Criteria) this;
        }

        public Criteria andRealPointNotBetween(Long value1, Long value2) {
            addCriterion("real_point not between", value1, value2, "realPoint");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNull() {
            addCriterion("confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNotNull() {
            addCriterion("confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeEqualTo(Date value) {
            addCriterion("confirm_time =", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotEqualTo(Date value) {
            addCriterion("confirm_time <>", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThan(Date value) {
            addCriterion("confirm_time >", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("confirm_time >=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThan(Date value) {
            addCriterion("confirm_time <", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThanOrEqualTo(Date value) {
            addCriterion("confirm_time <=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIn(List<Date> values) {
            addCriterion("confirm_time in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotIn(List<Date> values) {
            addCriterion("confirm_time not in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeBetween(Date value1, Date value2) {
            addCriterion("confirm_time between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotBetween(Date value1, Date value2) {
            addCriterion("confirm_time not between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNull() {
            addCriterion("invalid_time is null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNotNull() {
            addCriterion("invalid_time is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeEqualTo(Date value) {
            addCriterion("invalid_time =", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotEqualTo(Date value) {
            addCriterion("invalid_time <>", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThan(Date value) {
            addCriterion("invalid_time >", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("invalid_time >=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThan(Date value) {
            addCriterion("invalid_time <", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThanOrEqualTo(Date value) {
            addCriterion("invalid_time <=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIn(List<Date> values) {
            addCriterion("invalid_time in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotIn(List<Date> values) {
            addCriterion("invalid_time not in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeBetween(Date value1, Date value2) {
            addCriterion("invalid_time between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotBetween(Date value1, Date value2) {
            addCriterion("invalid_time not between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonIsNull() {
            addCriterion("invalid_reason is null");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonIsNotNull() {
            addCriterion("invalid_reason is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonEqualTo(String value) {
            addCriterion("invalid_reason =", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonNotEqualTo(String value) {
            addCriterion("invalid_reason <>", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonGreaterThan(String value) {
            addCriterion("invalid_reason >", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonGreaterThanOrEqualTo(String value) {
            addCriterion("invalid_reason >=", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonLessThan(String value) {
            addCriterion("invalid_reason <", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonLessThanOrEqualTo(String value) {
            addCriterion("invalid_reason <=", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonLike(String value) {
            addCriterion("invalid_reason like", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonNotLike(String value) {
            addCriterion("invalid_reason not like", value, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonIn(List<String> values) {
            addCriterion("invalid_reason in", values, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonNotIn(List<String> values) {
            addCriterion("invalid_reason not in", values, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonBetween(String value1, String value2) {
            addCriterion("invalid_reason between", value1, value2, "invalidReason");
            return (Criteria) this;
        }

        public Criteria andInvalidReasonNotBetween(String value1, String value2) {
            addCriterion("invalid_reason not between", value1, value2, "invalidReason");
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