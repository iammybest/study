package com.iammybest.common.http.code;


/**
 * 状态码
 *
 *
 * @version        1.0, 09/02/17
 * @author         Sundl
 */
public enum StateCode {

	Unknown(0),					//!< 未知错误

	ERROR(100),					//!< 请求失败
	NoData(101),				//!< 无数据
	VerificationCodeInvalid(102), //!< 验证吗失效

	Ok(200),					//!< 成功

	Accepted(202),				//!< 请求被处理，请等待
	NoContent(204),				//!< 已接收到请求，不需要返回数据

	BadRequest(400),			//!< 请求无效
	Unauthorized(401),			//!< 未授权请求
	PaymentRequired(402),		//!< 支付请求
	Forbidden(403),				//!< 服务器无法识别请求
	NotFound(404),				//!< 服务器没有找到对应的请求 URI
	MethodNotAllowed(405),		//!< 请求指定的方法服务器不允许执行
	NotAcceptable(406),			//!< 访问内容不被授权
	ProxyAuthenticationRequired(407),	//!< 代理需要授权
	RequestTimeout(408),		//!< 对端离线，请求超时
	Conflict(409),				//!< 有冲突的请求
	Gone(410),
	LengthRequired(411),
	PreconditionFailed(412),
	PayloadTooLarge(413),		//!< 负载数据过长
	UnsupportedMediaType(415),	//!< 不支持的媒体类型
	ExpectationFailed(417),
	TooManyRequests(429),		//!< 访问频率超过限制
	TemporarilyUnavailable(480),	//!< 暂时不可用

	ServerInternalError(500),	//!< 服务器内部错误
	GatewayTimeout(504),		//!< 网关超时

	InvalidParameter(704),		//!< 无效的参数
	ParamMiss(705),				//!< 参数缺失
	ParamIllegal(706),			//!< 非法参数 如大小 长度 类型不正确
	ParamPagerIllegal(707),		//!< 分页信息错误

	TokenFailed(800),			//!< token失效
	IncorrectState(801),		//!< 不正确的状态

	ContentTooLong(810),		//!< 内容长度越界
	BlockOversize(811),			//!< 大小超限
	FormatError(812),			//!< 格式错误
	ContentError(813),			//!< 内容错误
	UploadFailed(823),			//!< 上传文件失败
	ProcessFailed(825),			//!< 处理文件失败
	LoadFileFailed(828),		//!< 文件加载失败

	InvalidAccount(1001),		//!< 登录帐号超出授权错误
	RegisterTimeout(1002),		//!< 登录超时
	LicenseOutDate(1003),		//!< 授权过期
	// -----------------------------用户开始-------------------
	MobileIncorrect(10001),
	MobileRegistered(10002),
	MobileCodeIncorrect(10003),
	EmailIncorrect(10004),
	EmailRegistered(10005),
	AccountPasswordNoMatch(10006),
	EmailNoActivation(10007),      //!< 未激活邮箱
	AccountNotExist(10008),        //!< 账号不存在
	//------------------------------用户结束-------------------

	// -----------------------------群组开始-------------------
    UnauthorizedOperation(10401),   //!< 无权限操作群
	GroupAlreadyExists(10402),	    //!< 群已存在
	FailedDeleteGroup(10403),	    //!< 删除群组失败
	FailedModifyGroup(10404),	    //!< 群信息更新失败
	GroupExitFailed(10405),		    //!< 退出群失败
	GroupTransferFailed(10406),	    //!< 转让群失败
	ManagerAddFailed(10407),	    //!< 管理员添加失败
	ManagerDeleteFailed(10408),	    //!< 管理员删除失败
	ManagerAlreadyExists(10409),    //!< 管理员已存在
	MemberAddFailed(10410),		    //!< 群成员添加失败
	MemberDeleteFailed(10411),	    //!< 群成员删除失败
    MemberAlreadyExists(10412),	    //!< 群成员已存在
	NoticeModifyFailed(10413),	    //!< 公告更新失败
	CreateGroupFailed(10414),       //!< 群创建失败
	NoGroup(10415),			        //!< 查无此群
	NoMember(10416),                //!< 查无群成员
	GroupManagerMaxNum(10417),		//!< 群管理员数超过最大数量
	GroupMemberConfigFailed(10418),	//!< 群成员配置更新失败
	GroupAdministratorFailed(10419),//!< 修改群主别名失败
	NoManager(10420),                //!< 查无群管理员以及群主

	//------------------------------群组結束-------------------

	//------------------------------好友及分组开始-------------------
	FriendAddFailed(10201),			//!< 好友添加失败
	FriendExists(10202),			//!< 好友已存在
	FriendNotExists(10203),			//!< 好友不存在
	FriendDeleteFailed(10204),		//!< 好友删除失败
	FriendBatchDeleteFailed(10205),	//!< 好友批量删除失败
	FriendRemarkFailed(10206),		//!< 好友重命名失败
	FriendQueryFailed(10207),		//!< 好友查询失败
	FriendBatchQueryFailed(10208),	//!< 批量查询好友失败

	CategoryCreateFailed(10209),	//!< 分组创建失败
	CategoryExists(10210),			//!< 分组已存在
	CategoryNotExists(10211),		//!< 分组不存在
	CategoryDeleteFailed(10212),	//!< 分组删除失败
	CategoryQueryFailed(10213),		//!< 分组查询失败
	CategoryFriendsQueryFailed(10214),//!< 查询分组下的好友失败
	CategoryRenameFailed(10215),	//!< 分组重命名失败
	CategoryMoveFailed(10216),		//!< 移动好友至指定分组失败
	FriendNotInCategory(10217),		//!< 该分组中不存在该好友
	FriendIdsQueryFailed(10218),	//!< 查询好友ID列表失败
	//------------------------------好友及分组结束-------------------

	//------------------------------好友申请记录开始-------------------
	ApplicationAddFailed(10250),                    //!< 好友申请添加失败
	ApplicationQueryFailed(10251),               //!< 好友申请历史记录查询失败
	ApplicationQueryIdFailed(10252),          //!< 好友申请单个记录查询失败
	ApplicationNoDealFailed(10253),         //!< 好友申请未处理记录数量查询失败
	ApplicationDealFailed(10254),         //!< 好友申请处理他人请求失败
	ApplicationDelFailed(10255),        //!< 好友申请删除失败
	ApplicationClearFailed(10251),    //!< 好友申请清空失败
	NoApplication(10252),              //!<没有好友申请记录
	NoUser(10254),              //!<该用户不存在
	ApplicationDealExist(10255),         //!< 好友申请已经处理了

	//------------------------------好友申请记录结束-------------------

	//------------------------------用户意见反馈开始-------------------
	FeedbackAddFailed(10253),       //!< 用户意见反馈添加失败
	//------------------------------用户意见反馈结束-------------------



	//-----------------------------联系人---------------
	ContactCreateFailed(10601),        //导入联系人失败
	ContactQueryFailed(10602),   //查询联系人列表失败
	ContactQueryIdFailed(10603), //查询联系人id列表失败
	ContactQueryIdInfoFailed(10604), //根据id列表查询联系人信息失败
	ContactDeleteFailed(10605), //删除联系人失败
	ContactFormatFailed(10606), //导入联系人参数解析失败
	ContactDeleteNotFound(10607), //删除的联系人不存在

	//-----------------------------系统---------------
	ComplaintCreateFailed(11001), // 举报用户或群组失败
	DisturbCreateFailed(11002),   //创建免打扰设置失败
	DisturbQueryFailed(11003),    //获取免打扰设置失败
	UploadComplantImageFailed(11004),    //上传举报图片失败

	//-----------------------------找回、修改密码---------------
	TwicePasswordNoMatch(11201),  //两次密码不一致
	ResetUrlInvalid(11202),      //邮箱找回密码链接无效
	ResetPwdEmailFailed(11203),   //邮箱找回密码错误
	ResetPwdMobileFailed(11204),  //手机号找回密码错误
	ResetSendEmailFailed(11205),  //找回密码发送邮件失败
	ResetSendCodeFailed(11206),  //找回密码发送验证码失败
	UpdatePwdFailed(11207),  //找回密码发送验证码失败
	ResetCheckCodeFailed(11208), //验证找回密码验证码失败
	//----邮箱激活
	AccountActived(11301),
	LinkInvalid(11302),
	ActiveError(11303),
	//----异常拦截
	
	NullPointerException(2000),
	RuntimeException(2001),
	ClassCastException(2002),
	IOException(2003),
	NoSuchMethodException(2004),
	IndexOutOfBoundsException(2005),
	HttpMessageNotReadableException(2006),
	TypeMismatchException(2007),
	MissingServletRequestParameterException(2008);
	
	
	private int code;

	StateCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
