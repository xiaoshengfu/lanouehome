package com.huishoucat.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huishoucat.cms.service.IDistrictService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.FastDFSClient;
import com.huishoucat.common.utils.MyStringUtils;
import com.huishoucat.manager.pojo.TManager;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.ucenter.service.IManagerService;
import com.huishoucat.ucenter.service.IRecyclerService;
import com.huishoucat.ucenter.service.IUserAddressService;
import com.huishoucat.ucenter.service.IUserService;

/**
 * 用户管理Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 下午11:22:22
 * @version V1.0
 */
@Controller
public class UCenterController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRecyclerService recyclerService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IDistrictService districtService;
	@Autowired
	private IUserAddressService userAddressService;

	/**
	 * 查询用户列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/ucenter/user/list")
	@ResponseBody
	public PageBean<TUser> getUserList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return userService.getUserList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order, search);
	}

	/**
	 * 编辑用户信息
	 * @param user 更新数据
	 * @return
	 */
	@RequestMapping("/ucenter/user/edit")
	@ResponseBody
	public HuishoucatResult editUser(@RequestBody TUser user) {
		if (user != null && user.getUserId() != null) {
			user.setIsDeleted(null);
			return userService.updateUser(user);
		}
		return HuishoucatResult.HuishoucatResultError("用户ID不能为空！", null);
	}

	/**
	 * 删除用户
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/ucenter/user/delete")
	@ResponseBody
	public HuishoucatResult deleteUser(@RequestBody Long[] userIds) {
		if (userIds != null) {
			return userService.deleteUsersByUserId(userIds);
		}
		return HuishoucatResult.HuishoucatResultError("用户ID不能为空！", null);
	}

	/**
	 * 通过用户ID查找用户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/ucenter/user/find/{userId}")
	@ResponseBody
	public HuishoucatResult findUser(@PathVariable Long userId) {
		if (userId != null) {
			return userService.findUsersByUserId(userId);
		}
		return HuishoucatResult.HuishoucatResultError("用户ID不能为空！", null);
	}

	/**
	 * 通过回收员ID查找回收员
	 * @param recyclerId
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/find/{recyclerId}")
	@ResponseBody
	public HuishoucatResult findRecycle(@PathVariable Long recyclerId) {
		if (recyclerId != null) {
			return recyclerService.findRecyclerByRecyclerId(recyclerId);
		}
		return HuishoucatResult.HuishoucatResultError("回收员ID不能为空！", null);
	}

	/**
	 * 查询回收员列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/list")
	@ResponseBody
	public PageBean<TRecycler> getRecyclerList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search,
			HttpServletRequest request) {
		return recyclerService.getRecyclerList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑回收员信息
	 * @param recycler 更新数据
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/edit")
	@ResponseBody
	public HuishoucatResult editRecycler(@RequestBody TRecycler recycler) {
		if (recycler != null && recycler.getRecyclerId() != null) {
			recycler.setOpenid(null);
			recycler.setPictureUrl(null);
			recycler.setIsDeleted(null);
			return recyclerService.updateRecycler(recycler);
		}
		return HuishoucatResult.HuishoucatResultError("回收员ID不能为空！", null);
	}

	/**
	 * 删除回收员
	 * @param recyclerIds
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/delete")
	@ResponseBody
	public HuishoucatResult deleteRecycler(@RequestBody Long[] recyclerIds) {
		if (recyclerIds != null) {
			return recyclerService.deleteRecyclerByRecyclerId(recyclerIds);
		}
		return HuishoucatResult.HuishoucatResultError("回收员ID不能为空！", null);
	}

	/**
	 * 注册添加回收员
	 * @param recycler
	 * @param pictureFile
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/add")
	@ResponseBody
	public HuishoucatResult addRecycler(TRecycler recycler, MultipartFile pictureFile) {
		if (recycler != null && pictureFile != null) {
			if (StringUtils.isNotBlank(recycler.getTelephone()) && StringUtils.isNotBlank(recycler.getAddress())
					&& StringUtils.isNotBlank(recycler.getName()) && StringUtils.isNotBlank(recycler.getIdNum())
					&& StringUtils.isNotBlank(recycler.getPassword()) && recycler.getSex() != null
					&& recycler.getState() != null && StringUtils.isNotBlank(recycler.getDistrictIds())) {
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
					recycler.setPictureUrl(url);
				} catch (Exception e) {
					e.printStackTrace();
					return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
				}
				recycler.setRecyclerId(null);
				recycler.setIsDeleted(null);
				String[] strings = recycler.getDistrictIds().split(",");
				Long[] districtIds = {};
				Long districtId = null;
				HuishoucatResult result = null;
				for (int i = 0; i < strings.length; i++) {
					try {
						districtId = Long.parseLong(strings[i]);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						return HuishoucatResult.HuishoucatResultError("非法区域ID！", null);
					}
					result = districtService.findDistrictByDistrictId(districtId);
					if (result.getStateCode() != 200) {
						return HuishoucatResult.HuishoucatResultError("区域ID" + districtId + "无效！", null);
					} else {
						districtIds[i] = districtId;
					}
				}
				return recyclerService.addRecycler(recycler, districtIds);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 上传修改回收员照片
	 * @param pictureFile
	 * @param recyclerId
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/upload_picture/{recyclerId}")
	@ResponseBody
	public HuishoucatResult editRecyclerPicture(MultipartFile pictureFile, @PathVariable Long recyclerId) {
		try {
			if (recyclerId == null || pictureFile == null) {
				return HuishoucatResult.HuishoucatResultError("回收员ID或图片不能为空！", null);
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
			recyclerService.updateRecycler(new TRecycler(recyclerId, url));
			return HuishoucatResult.HuishoucatResultOK(null, url);
		} catch (Exception e) {
			e.printStackTrace();
			return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
		}
	}

	/**
	 * 查找回收员负责区域ID列表
	 * @param recyclerIds
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/find_district/{recyclerId}")
	@ResponseBody
	public HuishoucatResult deleteRecycler(@PathVariable Long recyclerId) {
		if (recyclerId != null) {
			return recyclerService.findRecyclerDistrictIds(recyclerId);
		}
		return HuishoucatResult.HuishoucatResultError("回收员ID不能为空！", null);
	}

	/**
	 * 修改回收员负责区域ID列表
	 * @param recyclerIds
	 * @return
	 */
	@RequestMapping("/ucenter/recycler/edit_district/{recyclerId}")
	@ResponseBody
	public HuishoucatResult updateRecycler(@PathVariable Long recyclerId, String stringDistrictIds) {
		if (StringUtils.isNotBlank(stringDistrictIds) && recyclerId != null) {
			String[] strings = stringDistrictIds.split(",");
			Long[] districtIds = {};
			Long districtId = null;
			HuishoucatResult result = null;
			for (int i = 0; i < strings.length; i++) {
				try {
					districtId = Long.parseLong(strings[i]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return HuishoucatResult.HuishoucatResultError("非法区域ID！", null);
				}
				result = districtService.findDistrictByDistrictId(districtId);
				if (result.getStateCode() != 200) {
					return HuishoucatResult.HuishoucatResultError("区域ID" + districtId + "无效！", null);
				} else {
					districtIds[i] = districtId;
				}
			}
			return recyclerService.updateRecyclerDistrictIds(recyclerId, districtIds);
		}
		return HuishoucatResult.HuishoucatResultError("回收员ID不能为空！", null);
	}

	/**
	 * 查询管理员列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/ucenter/manager/list")
	@ResponseBody
	public PageBean<TManager> getManagerList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search,
			HttpServletRequest request) {
		return managerService.getManagerList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort), order,
				search);
	}

	/**
	 * 编辑回收员信息
	 * @param user 更新数据
	 * @return
	 */
	@RequestMapping("/ucenter/manager/edit")
	@ResponseBody
	public HuishoucatResult editManager(@RequestBody TManager manager) {
		if (manager != null && manager.getManagerId() != null) {
			manager.setPictureUrl(null);
			manager.setIsDelete(null);
			return managerService.updateManager(manager);
		}
		return HuishoucatResult.HuishoucatResultError("管理员ID不能为空！", null);
	}

	/**
	 * 删除管理员
	 * @param managerIds
	 * @return
	 */
	@RequestMapping("/ucenter/manager/delete")
	@ResponseBody
	public HuishoucatResult deleteManager(@RequestBody Long[] managerIds) {
		if (managerIds != null) {
			return managerService.deleteManagerByManagerId(managerIds);
		}
		return HuishoucatResult.HuishoucatResultError("管理员ID不能为空！", null);
	}

	/**
	 * 注册添加管理员
	 * @param manager
	 * @param pictureFile
	 * @return
	 */
	@RequestMapping("/ucenter/manager/add")
	@ResponseBody
	public HuishoucatResult addManager(TManager manager, MultipartFile pictureFile) {
		if (manager != null && pictureFile != null) {
			if (StringUtils.isNotBlank(manager.getTelephone()) && StringUtils.isNotBlank(manager.getName())
					&& StringUtils.isNotBlank(manager.getPassword()) && manager.getSex() != null
					&& manager.getState() != null && manager.getJobNumber() != null) {
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
					manager.setPictureUrl(url);
				} catch (Exception e) {
					e.printStackTrace();
					return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
				}
				manager.setManagerId(null);
				manager.setIsDelete(null);
				return managerService.addManager(manager);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}

	/**
	 * 上传修改管理员照片
	 * @param pictureFile
	 * @param managerId
	 * @return
	 */
	@RequestMapping("/ucenter/manager/upload_picture/{managerId}")
	@ResponseBody
	public HuishoucatResult editManagerPicture(MultipartFile pictureFile, @PathVariable Long managerId) {
		try {
			if (managerId == null || pictureFile == null) {
				return HuishoucatResult.HuishoucatResultError("回收员ID或图片不能为空！", null);
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
			managerService.updateManager(new TManager(managerId, url));
			return HuishoucatResult.HuishoucatResultOK(null, url);
		} catch (Exception e) {
			e.printStackTrace();
			return HuishoucatResult.HuishoucatResultError("上传图片失败！", null);
		}
	}

	/**
	 * 通过用户地址ID查找用户地址信息
	 * @param userAddressId
	 * @return
	 */
	@RequestMapping("/ucenter/user_address/find/{userAddressId}")
	@ResponseBody
	public HuishoucatResult findUserAddress(@PathVariable Long userAddressId) {
		if (userAddressId != null) {
			return userAddressService.findUserAddressByUserAddressId(userAddressId);
		}
		return HuishoucatResult.HuishoucatResultError("用户地址ID不能为空！", null);
	}
}
