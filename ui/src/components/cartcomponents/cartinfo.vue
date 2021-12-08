<template>
  <el-row>
    <el-col
      v-for="(o) in 3"
      :key="o"
      :span="6"
      :offset="1"
    >
      <el-card :body-style="{ padding: '0px',width:'100%'}">
        <img
          src=""
          class="image"
        />
        <div style="padding: 14px">
          <span>Yummy hamburger</span>
          <div class="bottom">
            <time class="time">{{ currentDate }}</time>
            <el-button type="text" class="button">删除</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script scope>
import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup() {
    const currentDate = ref(new Date())

    return {
      currentDate,
    }
  },
})
</script>

<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  height: 200px;
  display: block;
}
</style>
