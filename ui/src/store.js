import { createStore } from 'vuex'
const store=createStore({
    state(){
        return{
            loginstate:"",
            userinfo:{},
            admininfo:{}
        }
    },
    mutations:{
            init(state){
                if(sessionStorage.getItem('loginstate')=='true'){
                    state.loginstate=sessionStorage.getItem('loginstate')
                    if(sessionStorage.getItem('userinfo')){
                        state.userinfo=JSON.parse(sessionStorage.getItem('userinfo')) 
                    }else{
                        state.admininfo=JSON.parse(sessionStorage.getItem('admininfo'))
                    }
                }else{
                    state.loginstate="false"
                }
            },
            setUserInfo(state,n){
                if(n.userinfo){
                state.loginstate=n.loginstate
                state.userinfo=n.userinfo
                sessionStorage.setItem("loginstate",state.loginstate )
                sessionStorage.setItem('userinfo',JSON.stringify(state.userinfo))
                }else if(n.admininfo){
                state.loginstate=n.loginstate
                state.admininfo=n.admininfo
                sessionStorage.setItem("loginstate",state.loginstate)
                sessionStorage.setItem('admininfo',JSON.stringify(state.admininfo))
                }
            },
            deleteAll(state){
                state.loginstate='false'
                state.userinfo=null
                state.admininfo=null
                sessionStorage.clear()
            }
    },
    getters:{
        // doneTodos: (state) => {
        //     return state.todos.filter(todo =>todo.done)
        //   },
        // doneTodosCount (state, getters) {
        //     return getters.doneTodos.length
        //   },
        // getTodoById: (state) => (id) => {
        //     return state.todos.find(todo => todo.id === id)
        //   },
        // getTOdoByDone:(state)=>(done)=>{
        //     return state.todos.find(todo=>todo.done===done)
        // }
    }
})
export default store