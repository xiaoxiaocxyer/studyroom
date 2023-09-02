import { addMessage } from "../../../api/message"
import pages from "../../../pages";
// subpackages/pages/message/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    choiceList: [
      {
        text: "异常反馈举报"
        
      },
      {
        text: "功能建议留言"
      }
    ],
    choiceIndex: 0,
    min: 0,
    max: 150,
    phoneValue: '',
    dataFrom: {
      messageType: "0",
      message: ""
    }
  },
  handChoice(row) {
    console.log(row.target.dataset.index)
    this.setData({
      choiceIndex: row.target.dataset.index,
      "dataFrom.messageType": row.target.dataset.index
    })
  },
  inputeExplain(e) {
    var value = e.detail.value;
    this.setData({
      "dataFrom.message": e.detail.value
    })
    let dataset = e.currentTarget.dataset;
    this.data[dataset.obj] = value;
    var len = parseInt(value.length);
    if (len > this.data.maxAddr) return;
    this.setData({
      currentWordNumber: len,
    });
    if (this.data.currentWordNumber == 150) {
      wx.showModal({
        title: "提示",
        content: "您输入的次数已达上限",
      });
    }
  },
  // 提交
  regionCheck() {
    if (!this.data.dataFrom.message) {
      wx.showToast({
        title: "请完善信息",
        icon: "none",
        duration: 2000,
      });
      return false
    }
    addMessage(this.data.dataFrom).then((info) =>{
      if (info.code == 0) {
        wx.showToast({
          title: '留言成功',
          icon: 'success',
          duration: 1500
        })
        setTimeout(() => {
          wx.switchTab({
            url: pages.My,
          });
        }, 500);
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})