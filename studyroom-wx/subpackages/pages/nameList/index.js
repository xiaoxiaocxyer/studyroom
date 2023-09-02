// subpackages/pages/announce/index.js
import {
  getAnnounceList,
  getForbanList,
  getMessageList
} from "../../../api/announce"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dataList: [],
    forbanList: [],
    messageList: []
  },
  // 公告
  getDataList() {
    getAnnounceList().then((info) =>{
      if (info.code == 0) {
        this.setData({
          dataList: info.data.content
        })
      }
    })
  },
  getUserList() {
    getForbanList().then((info) =>{
      if (info.code == 0) {
        this.setData({
          forbanList: info.data
        })
      }
    })
  },
  getLeavingList() {
    getMessageList().then((info) =>{
      if (info.code == 0) {
        this.setData({
          messageList: info.data
        })
      }
    })
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getDataList()
    this.getUserList()
    this.getLeavingList()
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