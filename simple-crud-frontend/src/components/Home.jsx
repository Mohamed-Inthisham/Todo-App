import axios from 'axios';
import React, { useEffect, useState } from 'react'
const api="http://localhost:8080"

function Home() {


  const [title, setTitle]=useState();
  const [description, setDescription]=useState();
  const [todos, setTodos]=useState([])

  useEffect(()=>{
    fetchAllTodos();
  },[todos])


  const createTodo = async ()=>{
    const tofo={title}
    

    try {
      const {data}=await axios.post(`${api}/create/todo`,tofo);
        setTodos([...todos,data])
        console.log(data)
      
    } catch (error) {
      console.log("catch error :", error) 
    }

  }

  const fetchAllTodos = async ()=>{

    try {
      const {data}=await axios.get(`${api}/todo`);
        setTodos(data)
        console.log("all todos",data)
      
    } catch (error) {
      console.log("catch error :", error) 
    }

  }
  const deleteTodo = async (id)=>{

    try {
      const {data}=await axios.delete(`${api}/todo/${id}`);
        setTodos(todos.filter(todo=>todo.id!== id))
        console.log("successfully deleted :",data)
      
    } catch (error) {
      console.log("catch error :", error) 
    }

  }
  return (
    <div>
        <div className="w-[50vw] h-[80vh] bg-white rounded-xl ">
      <div className="bg-[#758AA2] p-5 flex gap-5 justify-center rounded-t-xl">
        <input
          className="p-2 rounded-md w-full outline-none px-5 text-black"
          placeholder="Add New Task"
          type="text"
          onChange={(e)=>setTitle(e.target.value)}
        />
        <button onClick={createTodo} className="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">Add</button>
      </div>
      <h1 className="text-black text-center pt-10 font-bold">List Of Todo</h1>
      <div className="p-5 space-y-2 overflow-y-auto h-[60vh]">
        {todos.map((item, index) => (
          <div className="bg-[#99AAAB] p-3 rounded-md flex items-center justify-between">
            <div class="">
              <p class="text-gray-900 text-sm">
                {index + 1}. {item.title}
              </p>
            </div>
            <div class="flex space-x-4">
              <button
              onClick={() => deleteTodo(item.id)}
                class="text-red-600 hover:text-white focus:outline-none rounded-full hover:bg-red-600 p-2"
                aria-label="Delete"
              >
                <svg
                  class="h-6 w-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M6 18L18 6M6 6l12 12"
                  ></path>
                </svg>
              </button>
              <button ></button>
            </div>
          </div>
        ))}
      </div>
    </div>
    </div>
  )
}

export default Home