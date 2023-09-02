// app.js
App({
  onLaunch() {
    // wx.checkSession({
    //   success: function (res) {
    //     console.log(res, '登录未过期');
    //     wx.showToast({
    //       title: '登录未过期'
    //     });
    //   },
    // })
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    userInfo: null
  }
})
