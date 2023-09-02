import pages from "../../../pages";
import { register } from "../../../api/register";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataRegisterFrom: {
      account: "",
      password: "",
    },
    // 验证码按钮状态
    verificationStatus: true,
    // 用户协议单选
    radio: "",
    // 可见状态
    visible: true,
  },
  onChange(e) {
    if (e.currentTarget.dataset.sign == "account") {
      this.setData({
        "dataRegisterFrom.account": e.detail,
      });
    }
    if (e.currentTarget.dataset.sign == "password") {
      this.setData({
        "dataRegisterFrom.password": e.detail,
      });
    }
  },

  registration() {
    console.log(this.data.dataRegisterFrom);
    register(this.data.dataRegisterFrom).then((info) => {
      if (info.code == 0) {
        wx.showToast({
          title: "注册成功",
          icon: "success",
          duration: 2000,
        });
        setTimeout(() => {
          wx.navigateTo({
            url: pages.Login,
          });
        }, 500);
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {},

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},
});
