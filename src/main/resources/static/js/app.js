
    ////////////////////////////////
    //      EVENT MANAGEMENT      //
    ////////////////////////////////

document.addEventListener("DOMContentLoaded", () => {

    ////////////////////////////////
    //       NEW TODO LOGIC       //
    ////////////////////////////////

    const newTodoForm = document.getElementById('new-todo-form')

    newTodoForm.addEventListener( 'submit', (submitEvent) => {

        submitEvent.preventDefault()

        const newTask = document.getElementById("new-todo-task").value

        if (newTask) {

            try {
                    
                fetch( "/api/todo", {
                    method: "POST",
                    mode: 'cors',
                    cache: 'no-cache',
                    headers: {
                        "Content-Type": 'application/json'
                    },
                    body: JSON.stringify({
                        task: document.getElementById("new-todo-task").value
                    })
                }).then( newTodoResponse => newTodoResponse.json())
                .then( newTodo => {

                    const id = newTodo.id

                    const newTodoElement = document.createElement("li")
                    newTodoElement.classList.add("todo-item")
                    newTodoElement.id = `todo-${id}`

                    newTodoElement.innerHTML =  `
                        <h2 
                            class="todo-title"
                            id="title-${id}"
                            >${newTodo.task}</h2>
                        <div class="checkbox-container">
                            <button type="button" class="checkbox" id="checkbox-${id}">
                                <svg width="20" height="20" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
                                </svg>
                            </button>
                        </div>
                    `

                    document.querySelector("#todo-list").appendChild(newTodoElement)

                    const newCheckbox = document.getElementById(`checkbox-${id}`)

                    applyCheckboxListener(newCheckbox)

                    newTodoForm.reset()

                })

            } catch (e) {
                console.error(e.message);
            }

        }

    })


    ////////////////////////////////
    //     TOGGLE COMPLETION      //
    ////////////////////////////////

    function applyCheckboxListener(element) {

        element.addEventListener( 'click', clickEvent => {

            const todoId = element.id.substring(9)

            const isComplete = element.classList.contains("checkbox-checked") ? false : true;

            taskElementId = `title-${todoId}`

            const todoTaskTitle = document.getElementById(taskElementId)

            try {
                    
                fetch( "/api/todo/" + todoId , {
                    method: "PATCH",
                    mode: 'cors',
                    cache: 'no-cache',
                    headers: {
                        "Content-Type": 'application/json'
                    },
                    body: JSON.stringify({
                        complete: isComplete,
                        task: todoTaskTitle.innerText,
                    })
                }).then( res => res.json())
                .then( todo => console.log(todo))

                if ( isComplete ) {

                    element.classList.add("checkbox-checked")
                    
                    todoTaskTitle.style.textDecoration = "line-through"
                    
                } else {

                    element.classList.remove("checkbox-checked")

                    todoTaskTitle.style.textDecoration = "none"

                }

            } catch (e) {

                console.error(e.message);

            }

        })

    }

    const todoCheckboxes = document.querySelectorAll('.checkbox')

    todoCheckboxes.forEach( checkboxButton => {
        applyCheckboxListener( checkboxButton )
    })

})
