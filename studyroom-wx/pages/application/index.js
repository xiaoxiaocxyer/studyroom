import Dialog from "../../libs/vant-ui/dialog/dialog";
import {
  getLicationList,
  cancelLication,
  overLication,
} from "../../api/application";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    makeFlag: false,
    applyShow: false,
    dataList: [],
  },
  // 取消申请
  handCancel() {
    Dialog.confirm({
      message: "是否取消预约申请",
    })
      .then(() => {
        // on confirm
      })
      .catch(() => {
        // on cancel
      });
  },
  // 审核
  onApply(e) {
    let that = this
    wx.showModal({
      content: "确认取消/结束？",
      success(res) {
        if (res.confirm) {
          if (e.target.dataset.item.seatState == 0) {
            console.log("结束");
            cancelLication(e.target.dataset.item.id).then((info) => {
              if (info.code == 0) {
                wx.showToast({
                  title: "结束成功",
                  icon: "success",
                  duration: 1500,
                });
                that.geDataList();
              }
            });
          } else {
            overLication(e.target.dataset.item.id).then((info) => {
              if (info.code == 0) {
                wx.showToast({
                  title: "取消成功",
                  icon: "success",
                  duration: 1500,
                });
                that.geDataList();
              }
            });
          }
          
        } else if (res.cancel) {
          console.log("取消");
        }
      },
    });

    
  },
  geDataList() {
    getLicationList().then((info) => {
      if (info.code == 0) {
        this.setData({
          dataList: info.data,
        });
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
  onShow() {
    this.geDataList();
    if (typeof this.getTabBar === "function" && this.getTabBar()) {
      this.getTabBar().setData({
        //唯一标识（其它设置不同的整数）
        selected: 1,
      });
    }
  },

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
