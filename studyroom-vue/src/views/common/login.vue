<template>
  <div class="site-wrapper site-page--login">
    <div style="height:150px;width:100%;"></div>
    <div class="login-main">
      <div style="width:100%;height:20px;"></div>
      <div class="login-title" style="text-align:center;font-size: 20px;">自习室预约管理系统</div>
      <div style="width:100%;height:20px;"></div>
      <el-form style="margin-left:75px;" :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
        <el-form-item prop="userName" style="width:300px;">
          <el-input v-model="dataForm.userName" placeholder="帐号"></el-input>
        </el-form-item>
        <el-form-item prop="password" style="width:300px;">
          <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item prop="captcha" style="width:300px;">
          <el-row :gutter="20">
            <el-col :span="14">
              <el-input v-model="dataForm.captcha" placeholder="验证码">
              </el-input>
            </el-col>
            <el-col :span="10" class="login-captcha">
              <img :src="captchaPath" @click="getCaptcha()" alt="">
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item style="width:300px;">
          <el-button class="login-btn-submit" type="primary"
            @click="dataFormSubmit()">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getUUID } from '@/utils'
export default {
  data() {
    return {
      dataForm: {
        userName: '',
        password: '',
        uuid: '',
        captcha: ''
      },
      dataRule: {
        userName: [
          { required: true, message: '帐号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        captcha: [
          { required: true, message: '验证码不能为空', trigger: 'blur' }
        ]
      },
      captchaPath: ''
    }
  },
  created() {
    this.getCaptcha()
  },
  methods: {
    // 提交表单
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/sys/login'),
            method: 'post',
            data: this.$http.adornData({
              'username': this.dataForm.userName,
              'password': this.dataForm.password,
              'uuid': this.dataForm.uuid,
              'captcha': this.dataForm.captcha
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              console.log(data)
              this.$cookie.set('token', data.token)
              this.$router.replace({ name: 'home' })
            } else {
              this.getCaptcha()
              this.$message.error(data.msg)
            }
          })
        }
      })
    },
    // 获取验证码
    getCaptcha() {
      this.dataForm.uuid = getUUID()
      this.captchaPath = this.$http.adornUrl(`/captcha.jpg?uuid=${this.dataForm.uuid}`)
    }
  }
}
</script>

<style lang="scss">
.site-wrapper.site-page--login {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden;

  &:before {
    position: fixed;
    top: 0;
    left: 0;
    z-index: -1;
    width: 100%;
    height: 100%;
    content: "";
    background-image: url(~@/assets/img/login_bg.jpg);
    background-size: cover;
  }

  .site-content__wrapper {
    width: 100%;
    height: 100%;
  }

  .site-content {
    min-height: 100%;
  }

  .brand-info {
    margin: 220px 100px 0 90px;
    color: #fff;
  }

  .brand-info__text {
    margin: 0 0 22px 0;
    font-size: 48px;
    font-weight: 400;
    text-transform: uppercase;
  }

  .brand-info__intro {
    margin: 10px 0;
    font-size: 16px;
    line-height: 1.58;
    opacity: .6;
  }

  .login-main {
    border-radius: 20px;
    width: 450px;
    height: 320px;
    margin:0 auto;
    background-color: #fff;
  }

  .login-title {
    font-size: 16px;
  }

  .login-captcha {
    overflow: hidden;

    >img {
      width: 100%;
      cursor: pointer;
    }
  }

  .login-btn-submit {
    width: 100%;
  }
}</style>
