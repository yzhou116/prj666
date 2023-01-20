import VUE from 'vue'
import VUEX from 'vuex'

VUE.use(VUEX)

const state = {
  isPractice: false, //练习模式标志
  flag: false, //菜单栏左右滑动标志
  userInfo: null,
  menu: [{
      index: '1',
      title: 'Exam Management',
      icon: 'icon-kechengbiao',
      content:[{item1:'Functions',path:'/examDescription'},{item2:'Find Exam',path:'selectExam'},{item3:'Add Exam',path:'/addExam'}],
    },
    {
      index: '2',
      title: 'Repo Management',
      icon: 'icon-tiku',
      content:[{item1:'Functions',path:'/answerDescription'},{item2:'All Repos',path:'/selectAnswer'},{item3:'Add Repos',path:'/addAnswer'},{path: '/addAnswerChildren'}],
    },
    {
      index: '3',
      title: 'See Score',
      icon: 'icon-performance',
      content:[{item1:'Student Score',path:'/allStudentsGrade'},{path: '/grade'},{item2: 'See Score by result',path: '/selectExamToPart'},{path: '/scorePart'}],
    },
    {
      index: '4',
      title: 'Manage Student',
      icon: 'icon-role',
      content:[{item1:'Manege Student',path:'/studentManage'},{item2: 'Add Student',path: '/addStudent'}],
    },
    // {
    //   index: '5',
    //   title: '教师管理',
    //   icon: 'icon-Userselect',
    //   content:[{item1:'教师管理',path:'/teacherManage'},{item2: '添加教师',path: '/addTeacher'}],
    // },
    // {
    //   index: '7',
    //   title: '模块管理',
    //   icon: 'icon-module4mokuai',
    //   content:[{item1:'模块操作',path:'/module'}],
    // }
  ],
}
const mutations = {
  practice(state,status) {
    state.isPractice = status
  },
  toggle(state) {
    state.flag = !state.flag
  },
  changeUserInfo(state,info) {
    state.userInfo = info
  }
}
const getters = {
 
}
const actions = {
  getUserInfo(context,info) {
    context.commit('changeUserInfo',info)
  },
  getPractice(context,status) {
    context.commit('practice',status)
  }
}
export default new VUEX.Store({
  state,
  mutations,
  getters,
  actions,
  // store
})
