package com.mao.common.ext

import com.mao.base.loadService
import com.mao.common.autoservice.IWebViewInterface
import java.util.*

/**
 *
 * @author zhangkun
 * @time 2021/9/10 15:41
 * @Description
 */

val webViewService: IWebViewInterface = loadService<IWebViewInterface>()

