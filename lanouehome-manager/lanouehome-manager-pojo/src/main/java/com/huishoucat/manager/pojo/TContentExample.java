package com.huishoucat.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TContentExample() {
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

        public Criteria andContentIdIsNull() {
            addCriterion("content_id is null");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNotNull() {
            addCriterion("content_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentIdEqualTo(Long value) {
            addCriterion("content_id =", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotEqualTo(Long value) {
            addCriterion("content_id <>", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThan(Long value) {
            addCriterion("content_id >", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("content_id >=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThan(Long value) {
            addCriterion("content_id <", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThanOrEqualTo(Long value) {
            addCriterion("content_id <=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdIn(List<Long> values) {
            addCriterion("content_id in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotIn(List<Long> values) {
            addCriterion("content_id not in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdBetween(Long value1, Long value2) {
            addCriterion("content_id between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotBetween(Long value1, Long value2) {
            addCriterion("content_id not between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNull() {
            addCriterion("sub_title is null");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNotNull() {
            addCriterion("sub_title is not null");
            return (Criteria) this;
        }

        public Criteria andSubTitleEqualTo(String value) {
            addCriterion("sub_title =", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotEqualTo(String value) {
            addCriterion("sub_title <>", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThan(String value) {
            addCriterion("sub_title >", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("sub_title >=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThan(String value) {
            addCriterion("sub_title <", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThanOrEqualTo(String value) {
            addCriterion("sub_title <=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLike(String value) {
            addCriterion("sub_title like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotLike(String value) {
            addCriterion("sub_title not like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleIn(List<String> values) {
            addCriterion("sub_title in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotIn(List<String> values) {
            addCriterion("sub_title not in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleBetween(String value1, String value2) {
            addCriterion("sub_title between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotBetween(String value1, String value2) {
            addCriterion("sub_title not between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andTitleDescIsNull() {
            addCriterion("title_desc is null");
            return (Criteria) this;
        }

        public Criteria andTitleDescIsNotNull() {
            addCriterion("title_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTitleDescEqualTo(String value) {
            addCriterion("title_desc =", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotEqualTo(String value) {
            addCriterion("title_desc <>", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescGreaterThan(String value) {
            addCriterion("title_desc >", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescGreaterThanOrEqualTo(String value) {
            addCriterion("title_desc >=", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescLessThan(String value) {
            addCriterion("title_desc <", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescLessThanOrEqualTo(String value) {
            addCriterion("title_desc <=", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescLike(String value) {
            addCriterion("title_desc like", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotLike(String value) {
            addCriterion("title_desc not like", value, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescIn(List<String> values) {
            addCriterion("title_desc in", values, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotIn(List<String> values) {
            addCriterion("title_desc not in", values, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescBetween(String value1, String value2) {
            addCriterion("title_desc between", value1, value2, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andTitleDescNotBetween(String value1, String value2) {
            addCriterion("title_desc not between", value1, value2, "titleDesc");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPic2IsNull() {
            addCriterion("pic2 is null");
            return (Criteria) this;
        }

        public Criteria andPic2IsNotNull() {
            addCriterion("pic2 is not null");
            return (Criteria) this;
        }

        public Criteria andPic2EqualTo(String value) {
            addCriterion("pic2 =", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotEqualTo(String value) {
            addCriterion("pic2 <>", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2GreaterThan(String value) {
            addCriterion("pic2 >", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2GreaterThanOrEqualTo(String value) {
            addCriterion("pic2 >=", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2LessThan(String value) {
            addCriterion("pic2 <", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2LessThanOrEqualTo(String value) {
            addCriterion("pic2 <=", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2Like(String value) {
            addCriterion("pic2 like", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotLike(String value) {
            addCriterion("pic2 not like", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2In(List<String> values) {
            addCriterion("pic2 in", values, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotIn(List<String> values) {
            addCriterion("pic2 not in", values, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2Between(String value1, String value2) {
            addCriterion("pic2 between", value1, value2, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotBetween(String value1, String value2) {
            addCriterion("pic2 not between", value1, value2, "pic2");
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