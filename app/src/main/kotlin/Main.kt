import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

data class Employee(
    val id: Int,
    val name: String,
    val department: String
)

sealed interface EmployeeUiState {
    data object Loading : EmployeeUiState
    data class Success(val employees: List<Employee>) : EmployeeUiState
    data object Empty : EmployeeUiState
    data class Error(val message: String) : EmployeeUiState
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kotlin UI State Management"
    ) {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                EmployeeScreen()
            }
        }
    }
}

@Composable
fun EmployeeScreen() {
    var uiState by remember {
        mutableStateOf<EmployeeUiState>(
            EmployeeUiState.Success(
                listOf(
                    Employee(1, "Ravi", "IT"),
                    Employee(2, "Priya", "HR")
                )
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Employee Management", style = MaterialTheme.typography.h5)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { uiState = EmployeeUiState.Loading }) {
                Text("Loading")
            }
            Button(onClick = {
                uiState = EmployeeUiState.Success(
                    listOf(
                        Employee(1, "Ravi", "IT"),
                        Employee(2, "Priya", "HR")
                    )
                )
            }) {
                Text("Load Employees")
            }
            Button(onClick = { uiState = EmployeeUiState.Empty }) {
                Text("Show Empty")
            }
            Button(onClick = {
                uiState = EmployeeUiState.Error("Unable to load employees")
            }) {
                Text("Show Error")
            }
        }

        Divider()
        when (val state = uiState) {
            is EmployeeUiState.Loading -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator()
                    Spacer(Modifier.padding(8.dp))
                    Text("Loading employees...")
                }
            }

            is EmployeeUiState.Success -> {
                Text("Employees loaded successfully")
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.employees) { employee ->
                        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Text("ID: ${employee.id}")
                            Text("Name: ${employee.name}")
                            Text("Department: ${employee.department}")
                        }
                    }
                }
            }

            is EmployeeUiState.Empty -> {
                Text("No employees found.")
                Text("Add an employee to see the list here.")
            }

            is EmployeeUiState.Error -> {
                Text("Error: ${state.message}")
                Button(onClick = { uiState = EmployeeUiState.Loading }) {
                    Text("Try Again (shows Loading state)")
                }
            }
        }
    }
}
