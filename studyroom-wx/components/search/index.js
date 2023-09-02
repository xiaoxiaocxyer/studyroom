// components/search/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },
  //开启多个插槽
  // options: {
  //   multipleSlots: true
  // },
  /**
   * 组件的初始数据
   */
  data: {
    value: ""
  },

  /**
   * 组件的方法列表
   */
  methods: {
    onSearch(res) {
      console.log(res)
      this.triggerEvent('searchevent', {
        name: this.data.value,
      })
    }
  }
})
