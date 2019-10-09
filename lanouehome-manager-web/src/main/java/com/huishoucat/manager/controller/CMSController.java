package com.huishoucat.manager.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huishoucat.cms.service.IBulletinService;
import com.huishoucat.cms.service.ICategoryService;
import com.huishoucat.cms.service.IContentService;
import com.huishoucat.cms.service.IDistrictService;
import com.huishoucat.cms.service.IItemDescService;
import com.huishoucat.cms.service.IWasteAttributeService;
import com.huishoucat.cms.service.IWastePriceService;
import com.huishoucat.cms.service.IWasteService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.FastDFSClient;
import com.huishoucat.common.utils.MyStringUtils;
import com.huishoucat.manager.pojo.TBulletin;
import com.huishoucat.manager.pojo.TCategory;
import com.huishoucat.manager.pojo.TContent;
import com.huishoucat.manager.pojo.TDistrict;
import com.huishoucat.manager.pojo.TItemDesc;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteAttribute;
import com.huishoucat.manager.pojo.TWastePrice;

/**
 * 内容管理系统Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月17日 下午8:03:28
 * @version V1.0
 */
@Controller
public class CMSController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private IDistrictService districtService;
	@Autowired
	private IWasteService wasteService;
	@Autowired
	private IWastePriceService wastePriceService;
	@Autowired
	private IWasteAttributeService wasteAttributeService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IContentService contentService;
	@Autowired
	private IItemDescService itemDescService;
	@Autowired
	private IBulletinService bulletinService;

	/**
	 * 查询废品属性列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/waste_attribute/list")
	@ResponseBody
	public PageBean<TWasteAttribute> getWasteAttributeList(
			@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "sort") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return wasteAttributeService.getWasteAttributeList(offset / limit + 1, limit,
				MyStringUtils.underscoreminName(sort), order, search);
	}

	/**
	 * 编辑废品属性信息
	 * @param wasteAttribute 更新数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/cms/waste_attribute/edit")
	@ResponseBody
	public HuishoucatResult editWasteAttribute(@RequestBody TWasteAttribute wasteAttribute)
			throws UnsupportedEncodingException {
		if (wasteAttribute != null && wasteAttribute.getWasteAttributeId() != null) {
			/*
			 * wasteAttribute.setAttributeContent( new
			 * String(wasteAttribute.getAttributeContent().getBytes("ISO8859-1")
			 * , "UTF-8"));
			 */
			if (wasteAttribute.getWasteId() != null) {
				Integer code = wasteService.findWasteByWasteId(wasteAttribute.getWasteId()).getStateCode();
				if (code != HuishoucatResult.SUCCESS) {
					return HuishoucatResult.HuishoucatResultError("无效废品ID！", null);
				}
			}
			wasteAttribute.setIsDeleted(null);
			return wasteAttributeService.updateWasteAttribute(wasteAttribute);
		}
		return HuishoucatResult.HuishoucatResultError("废品属性ID不能为空！", null);
	}

	/**
	 * 删除废品属性
	 * @param wasteAttributeIds
	 * @return
	 */
	@RequestMapping("/cms/waste_attribute/delete")
	@ResponseBody
	public HuishoucatResult deleteWasteAttribute(@RequestBody Long[] wasteAttributeIds) {
		if (wasteAttributeIds != null) {
			return wasteAttributeService.deleteWasteAttributeByWasteAttributeId(wasteAttributeIds);
		}
		return HuishoucatResult.HuishoucatResultError("废品属性ID不能为空！", null);
	}

	/**
	 * 添加废品属性
	 * @param wasteAttribute
	 * @return
	 */
	@RequestMapping("/cms/waste_attribute/add")
	@ResponseBody
	public HuishoucatResult addWasteAttribute(@RequestBody TWasteAttribute wasteAttribute) {
		if (wasteAttribute != null) {
			if (StringUtils.isNotBlank(wasteAttribute.getAttributeContent()) && wasteAttribute.getWasteId() != null) {
				Integer code = wasteService.findWasteByWasteId(wasteAttribute.getWasteId()).getStateCode();
				if (code != HuishoucatResult.SUCCESS) {
					return HuishoucatResult.HuishoucatResultError("无效废品ID！", null);
				}
				wasteAttribute.setIsDeleted(null);
				wasteAttribute.setWasteAttributeId(null);
				return wasteAttributeService.addWasteAttribute(wasteAttribute);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 查询区域列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/district/list")
	@ResponseBody
	public PageBean<TDistrict> getDistrictList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "sort") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		// String transcodingSearch = null;
		/*
		 * try { if (StringUtils.isNotBlank(search)) { transcodingSearch = new
		 * String(search.getBytes("ISO8859-1"), "UTF-8"); } } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		return districtService.getDistrictList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑区域信息
	 * @param district 更新数据
	 * @return
	 */
	@RequestMapping("/cms/district/edit")
	@ResponseBody
	public HuishoucatResult editDistrict(@RequestBody TDistrict district) {
		if (district != null && district.getDistrictId() != null) {
			district.setSort(null);
			district.setIsDeleted(null);
			return districtService.updateDistrict(district);
		}
		return HuishoucatResult.HuishoucatResultError("区域ID不能为空！", null);
	}

	/**
	 * 删除区域
	 * @param districtIds
	 * @return
	 */
	@RequestMapping("/cms/district/delete")
	@ResponseBody
	public HuishoucatResult deleteDistrict(@RequestBody Long[] districtIds) {
		if (districtIds != null) {
			return districtService.deleteDistrictByDistrictId(districtIds);
		}
		return HuishoucatResult.HuishoucatResultError("区域ID不能为空！", null);
	}

	/**
	 * 添加区域
	 * @param district
	 * @return
	 */
	@RequestMapping("/cms/district/add")
	@ResponseBody
	public HuishoucatResult addRecycler(@RequestBody TDistrict district) {
		if (district != null) {
			if (StringUtils.isNotBlank(district.getDistrictName()) && StringUtils.isNotBlank(district.getShortName())
					&& district.getParentId() != null && district.getLatitude() != null
					&& district.getLongitude() != null && district.getSort() != null) {
				district.setIsDeleted(null);
				district.setDistrictId(null);
				return districtService.addDistrict(district);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 查询废品列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/cms/waste/list")
	@ResponseBody
	public PageBean<TWaste> getWasteList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search,
			HttpServletRequest request) throws UnsupportedEncodingException {
		/*
		 * if (StringUtils.isNotBlank(search)) { search = new
		 * String(search.getBytes("ISO8859-1"), "UTF-8"); }
		 */
		return wasteService.getWasteList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑废品信息
	 * @param waste 更新数据
	 * @return
	 */
	@RequestMapping("/cms/waste/edit")
	@ResponseBody
	public HuishoucatResult editWaste(@RequestBody TWaste waste) {
		if (waste != null && waste.getWasteId() != null) {
			waste.setIsParent(null);
			waste.setLevel(null);
			waste.setIsDeleted(null);
			return wasteService.updateWaste(waste);
		}
		return HuishoucatResult.HuishoucatResultError("废品ID不能为空！", null);
	}

	/**
	 * 上传修改废品照片
	 * @param pictureFile
	 * @param wasteId
	 * @return
	 */
	@RequestMapping("/cms/waste/upload_picture/{wasteId}")
	@ResponseBody
	public HuishoucatResult editWastePicture(MultipartFile pictureFile, @PathVariable Long wasteId) {
		try {
			if (wasteId == null || pictureFile == null) {
				return HuishoucatResult.HuishoucatResultError("废品ID或图片不能为空！", null);
			}
			// 把图片上传的图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			// 取文件扩展名
			String originalFilename = pictureFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			// 得到一个图片的地址和文件名
			String url = fastDFSClient.uploadFile(pictureFile.getBytes(), extName);
			if (url == null) {
				return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
			}
			wasteService.updateWaste(new TWaste(wasteId, url));
			return HuishoucatResult.HuishoucatResultOK(null, url);
		} catch (Exception e) {
			e.printStackTrace();
			return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
		}
	}

	/**
	 * 删除废品
	 * @param wasteIds
	 * @return
	 */
	@RequestMapping("/cms/waste/delete")
	@ResponseBody
	public HuishoucatResult deleteWaste(@RequestBody Long[] wasteIds) {
		if (wasteIds != null) {
			return wasteService.deleteWasteByWasteId(wasteIds);
		}
		return HuishoucatResult.HuishoucatResultError("废品ID不能为空！", null);
	}

	/**
	 * 添加废品
	 * @param waste
	 * @param pictureFile
	 * @return
	 */
	@RequestMapping("/cms/waste/add")
	@ResponseBody
	public HuishoucatResult addWaste(TWaste waste, MultipartFile pictureFile) {
		if (waste != null && pictureFile != null && StringUtils.isNotBlank(waste.getName())
				&& waste.getParentId() != null && waste.getUnitPoint() != null) {
			try {
				// 把图片上传的图片服务器
				FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
				// 取文件扩展名
				String originalFilename = pictureFile.getOriginalFilename();
				String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
				// 得到一个图片的地址和文件名
				String url = fastDFSClient.uploadFile(pictureFile.getBytes(), extName);
				if (url == null) {
					return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
				}
				waste.setPictureUrl(url);
				waste.setIsDeleted(null);
				waste.setIsParent(null);
				return wasteService.addWaste(waste);
			} catch (Exception e) {
				e.printStackTrace();
				return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 查询废品价格列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/waste_price/list")
	@ResponseBody
	public PageBean<TWastePrice> getWastePriceList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search,
			HttpServletRequest request) {
		return wastePriceService.getWastePriceList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort),
				order, search);
	}

	/**
	 * 编辑废品价格信息
	 * @param wastePrice 更新数据
	 * @return
	 */
	@RequestMapping("/cms/waste_price/edit")
	@ResponseBody
	public HuishoucatResult editDistrict(@RequestBody TWastePrice wastePrice) {
		if (wastePrice != null && wastePrice.getWastePriceId() != null) {
			wastePrice.setIsDeleted(null);
			return wastePriceService.updateWastePrice(wastePrice);
		}
		return HuishoucatResult.HuishoucatResultError("废品价格ID不能为空！", null);
	}

	/**
	 * 删除废品价格信息
	 * @param wastePriceIds
	 * @return
	 */
	@RequestMapping("/cms/waste_price/delete")
	@ResponseBody
	public HuishoucatResult deleteWastePrice(@RequestBody Long[] wastePriceIds) {
		if (wastePriceIds != null) {
			return wastePriceService.deleteWastePriceByWastePriceId(wastePriceIds);
		}
		return HuishoucatResult.HuishoucatResultError("废品价格ID不能为空！", null);
	}

	/**
	 * 添加废品价格
	 * @param wastePrice
	 * @return
	 */
	@RequestMapping("/cms/waste_price/add")
	@ResponseBody
	public HuishoucatResult addWastePrice(@RequestBody TWastePrice wastePrice) {
		if (wastePrice != null && wastePrice.getPriceCeiling() != null && wastePrice.getPriceFloor() != null
				&& wastePrice.getWasteId() != null && wastePrice.getUnit() != null) {
			if (wasteService.findWasteByWasteId(wastePrice.getWasteId()).getStateCode() == HuishoucatResult.SUCCESS) {
				return wastePriceService.addWastePrice(wastePrice);
			} else {
				return HuishoucatResult.HuishoucatResultError("无效废品ID！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 查询类目列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/category/list")
	@ResponseBody
	public PageBean<TCategory> getCategoryList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "sort") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return categoryService.getCategoryList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑类目信息
	 * @param category 更新数据
	 * @return
	 */
	@RequestMapping("/cms/category/edit")
	@ResponseBody
	public HuishoucatResult editCategory(@RequestBody TCategory category) {
		if (category != null && category.getCategoryId() != null) {
			category.setState(null);
			category.setIsDeleted(null);
			return categoryService.updateCategory(category);
		}
		return HuishoucatResult.HuishoucatResultError("类目ID不能为空！", null);
	}

	/**
	 * 删除类目
	 * @param categoryIds
	 * @return
	 */
	@RequestMapping("/cms/category/delete")
	@ResponseBody
	public HuishoucatResult deleteCategory(@RequestBody Long[] categoryIds) {
		if (categoryIds != null) {
			return categoryService.deleteCategoryByCategoryId(categoryIds);
		}
		return HuishoucatResult.HuishoucatResultError("类目ID不能为空！", null);
	}

	/**
	 * 添加类目
	 * @param category
	 * @return
	 */
	@RequestMapping("/cms/category/add")
	@ResponseBody
	public HuishoucatResult addCategory(@RequestBody TCategory category) {
		if (category != null) {
			if (StringUtils.isNotBlank(category.getName()) && category.getParentId() != null
					&& category.getSortOrder() != null) {
				if (category.getParentId() != 0 && categoryService.findCategoryByCategoryId(category.getParentId())
						.getStateCode() != HuishoucatResult.SUCCESS) {
					return HuishoucatResult.HuishoucatResultError("无效类目ID！", null);
				}
				category.setCategoryId(null);
				return categoryService.addCategory(category);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 查询内容列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/content/list")
	@ResponseBody
	public PageBean<TContent> getContentList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "sort") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return contentService.getContentList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑内容信息
	 * @param content 更新数据
	 * @return
	 */
	@RequestMapping("/cms/content/edit")
	@ResponseBody
	public HuishoucatResult editContent(@RequestBody TContent content) {
		if (content != null && content.getContentId() != null) {
			content.setIsDeleted(null);
			return contentService.updateContent(content);
		}
		return HuishoucatResult.HuishoucatResultError("内容ID不能为空！", null);
	}

	/**
	 * 删除内容
	 * @param content
	 * @return
	 */
	@RequestMapping("/cms/content/delete")
	@ResponseBody
	public HuishoucatResult deleteContent(@RequestBody Long[] contentIds) {
		if (contentIds != null) {
			return contentService.deleteContentByContentId(contentIds);
		}
		return HuishoucatResult.HuishoucatResultError("内容ID不能为空！", null);
	}

	/**
	 * 添加类目内容
	 * @param content
	 * @param pictureFile
	 * @return
	 */
	@RequestMapping("/cms/content/add")
	@ResponseBody
	public HuishoucatResult addContent(TContent content, MultipartFile pictureFile) {
		if (content != null && pictureFile != null) {
			if (StringUtils.isNotBlank(content.getTitle()) && content.getCategoryId() != null) {

				try {
					// 把图片上传的图片服务器
					FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
					// 取文件扩展名
					String originalFilename = pictureFile.getOriginalFilename();
					String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
					// 得到一个图片的地址和文件名
					String url = fastDFSClient.uploadFile(pictureFile.getBytes(), extName);
					if (url == null) {
						return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
					}
					content.setPic(url);
				} catch (Exception e) {
					e.printStackTrace();
					return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
				}
				content.setContentId(null);
				content.setIsDeleted(null);
				return contentService.addContent(content);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 上传修改类目内容照片
	 * @param pictureFile
	 * @param contentId
	 * @return
	 */
	@RequestMapping("/cms/content/upload_picture/{contentId}")
	@ResponseBody
	public HuishoucatResult editContentPicture(MultipartFile pictureFile, @PathVariable Long contentId) {
		try {
			if (contentId == null || pictureFile == null) {
				return HuishoucatResult.HuishoucatResultError("内容ID或图片不能为空！", null);
			}
			// 把图片上传的图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			// 取文件扩展名
			String originalFilename = pictureFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			// 得到一个图片的地址和文件名
			String url = fastDFSClient.uploadFile(pictureFile.getBytes(), extName);
			if (url == null) {
				return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
			}
			contentService.updateContent(new TContent(contentId, url));
			return HuishoucatResult.HuishoucatResultOK(null, url);
		} catch (Exception e) {
			e.printStackTrace();
			return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
		}
	}

	/**
	 * 查询积分商品列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/item_desc/list")
	@ResponseBody
	public PageBean<TItemDesc> getItemDescList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "sort") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		// String transcodingSearch = null;
		/*
		 * try { if (StringUtils.isNotBlank(search)) { transcodingSearch = new
		 * String(search.getBytes("ISO8859-1"), "UTF-8"); } } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		return itemDescService.getItemDescList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑积分商品信息
	 * @param itemDesc 更新数据
	 * @return
	 */
	@RequestMapping("/cms/item_desc/edit")
	@ResponseBody
	public HuishoucatResult editItemDesc(TItemDesc itemDesc, MultipartFile[] pictureFiles) {
		if (itemDesc != null && itemDesc.getItemId() != null) {
			if (pictureFiles != null) {
				try {
					// 把图片上传的图片服务器
					FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
					String urls = "";
					for (int i = 0; i < pictureFiles.length; i++) {
						// 取文件扩展名
						String originalFilename = pictureFiles[i].getOriginalFilename();
						String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
						// 得到一个图片的地址和文件名
						String url = fastDFSClient.uploadFile(pictureFiles[i].getBytes(), extName);
						if (url == null) {
							return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
						}
						if (!urls.equals("")) {
							urls = urls + ",";
						}
						urls += url;
					}
					itemDesc.setPictureUrl(urls);
				} catch (Exception e) {
					e.printStackTrace();
					return HuishoucatResult.HuishoucatResultError("上传积分商品图片失败！", null);
				}
			} else {
				itemDesc.setPictureUrl(null);
			}
			itemDesc.setIsDeleted(null);
			return itemDescService.updateItemDesc(itemDesc);
		}
		return HuishoucatResult.HuishoucatResultError("积分商品ID不能为空！", null);
	}

	/**
	 * 删除积分商品
	 * @param itemIds
	 * @return
	 */
	@RequestMapping("/cms/item_desc/delete")
	@ResponseBody
	public HuishoucatResult deleteItemDesc(@RequestBody Long[] itemIds) {
		if (itemIds != null) {
			return itemDescService.deleteItemDescByItemId(itemIds);
		}
		return HuishoucatResult.HuishoucatResultError("积分商品ID不能为空！", null);
	}

	/**
	 * wangEditor3上传图片
	 * @param pictureFile
	 * @return
	 */
	@RequestMapping("/cms/editor/upload_pictures")
	@ResponseBody
	public Map<String, Object> uploadPicture(MultipartFile[] pictureFiles) {
		int errno = 0;
		String[] urls = new String[pictureFiles.length];
		try {
			if (pictureFiles == null || pictureFiles.length == 0) {
				errno = 400;
			} else {
				// 把图片上传的图片服务器
				FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
				for (int i = 0; i < pictureFiles.length; i++) {
					// 取文件扩展名
					String originalFilename = pictureFiles[i].getOriginalFilename();
					String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
					// 得到一个图片的地址和文件名
					String url = fastDFSClient.uploadFile(pictureFiles[i].getBytes(), extName);
					if (url == null) {
						errno = 500;
						break;
					}
					urls[i] = IMAGE_SERVER_URL + url;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errno = 500;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errno", errno);
		map.put("data", urls);
		return map;
	}

	/**
	 * 添加积分商品
	 * @param itemDesc
	 * @param pictureFile
	 * @return
	 */
	@RequestMapping("/cms/item_desc/add")
	@ResponseBody
	public HuishoucatResult addItemDesc(TItemDesc itemDesc, MultipartFile[] pictureFiles) {
		if (itemDesc != null && pictureFiles != null) {
			if (StringUtils.isNotBlank(itemDesc.getName()) && itemDesc.getPrice() != null
					&& itemDesc.getStock() != null) {
				try {
					// 把图片上传的图片服务器
					FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
					String urls = "";
					for (int i = 0; i < pictureFiles.length; i++) {
						// 取文件扩展名
						String originalFilename = pictureFiles[i].getOriginalFilename();
						String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
						// 得到一个图片的地址和文件名
						String url = fastDFSClient.uploadFile(pictureFiles[i].getBytes(), extName);
						if (url == null) {
							return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
						}
						if (!urls.equals("")) {
							urls = urls + ",";
						}
						urls += url;
					}
					itemDesc.setPictureUrl(urls);
				} catch (Exception e) {
					e.printStackTrace();
					return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
				}
				itemDesc.setItemId(null);
				itemDesc.setIsDeleted(null);
				// 类型:1正常,2库存不足,3下架
				if (itemDesc.getStock() > 0) {
					itemDesc.setState(1);
				} else {
					itemDesc.setState(2);
				}
				return itemDescService.addItemDesc(itemDesc);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 查询公告列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/cms/bulletin/list")
	@ResponseBody
	public PageBean<TBulletin> getBulletinList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "sort") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		// String transcodingSearch = null;
		/*
		 * try { if (StringUtils.isNotBlank(search)) { transcodingSearch = new
		 * String(search.getBytes("ISO8859-1"), "UTF-8"); } } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		return bulletinService.getBulletinList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑公告信息
	 * @param district 更新数据
	 * @return
	 */
	@RequestMapping("/cms/bulletin/edit")
	@ResponseBody
	public HuishoucatResult editBulletin(@RequestBody TBulletin bulletin) {
		if (bulletin != null && bulletin.getBulletinId() != null) {
			bulletin.setIsDeleted(null);
			return bulletinService.updateBulletin(bulletin);
		}
		return HuishoucatResult.HuishoucatResultError("公告ID不能为空！", null);
	}

	/**
	 * 删除公告
	 * @param bulletinIds
	 * @return
	 */
	@RequestMapping("/cms/bulletin/delete")
	@ResponseBody
	public HuishoucatResult deleteBulletin(@RequestBody Long[] bulletinIds) {
		if (bulletinIds != null) {
			return bulletinService.deleteBulletinByBulletinId(bulletinIds);
		}
		return HuishoucatResult.HuishoucatResultError("公告ID不能为空！", null);
	}

	/**
	 * 添加公告
	 * @param bulletin
	 * @return
	 */
	@RequestMapping("/cms/bulletin/add")
	@ResponseBody
	public HuishoucatResult addBulletin(@RequestBody TBulletin bulletin) {
		if (bulletin != null) {
			if (StringUtils.isNotBlank(bulletin.getBulletinTitle())
					&& StringUtils.isNotBlank(bulletin.getBulletinContent()) && bulletin.getSort() != null) {
				bulletin.setBulletinId(null);
				bulletin.setIsDeleted(null);
				return bulletinService.addBulletin(bulletin);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}
}
