// subpackages/pages/information/index.js
import pages from "../../../pages";
import { userInfo, userUpdate } from "../../../api/information";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    dataFrom: {
      mobile: "",
      name: "",
      userImg: "",
      email: "",
      qq: "",
    },
    //信息输入状态
    fromShow: false,
    min: 0,
    max: 100,
  },
  // 提交
  regionCheck() {
    userUpdate(this.data.dataFrom).then((info) =>{
      if (info.code == 0) {
        wx.showToast({
          title: "修改成功",
          icon: "success",
          duration: 2000,
        });
        setTimeout(() => {
          wx.switchTab({
            url: pages.My,
          });
        }, 500);
      }
    })
  },
  inputeExplain(e) {
    console.log(e.detail.value)
    this.setData({
      "dataFrom.bz": e.detail.value
    })
    var value = e.detail.value;
    let dataset = e.currentTarget.dataset;
    this.data[dataset.obj] = value;
    var len = parseInt(value.length);
    if (len > this.data.maxAddr) return;
    this.setData({
      currentWordNumber: len,
    });
    if (this.data.currentWordNumber == 100) {
      wx.showModal({
        title: "提示",
        content: "您输入的次数已达上限",
      });
    }
  },


  onChange(e) {
    if (e.target.dataset.sign == "name") { 
      this.setData({
        "dataFrom.name": e.detail.value
      })
    }
    if (e.target.dataset.sign == "qq") { 
      this.setData({
        "dataFrom.qq": e.detail.value
      })
    }
    if (e.target.dataset.sign == "email") { 
      this.setData({
        "dataFrom.email": e.detail.value
      })
    }
    if (e.target.dataset.sign == "mobile") { 
      this.setData({
        "dataFrom.mobile": e.detail.value
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    userInfo().then((info) =>{
      console.log(info)
      if (info.code == 0) {
        this.setData({
          dataFrom: info.data
        })
      }
    })
  },

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

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},
});
