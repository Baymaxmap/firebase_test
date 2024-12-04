package com.example.firebase_test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase_test.model.AuthRepository
import com.example.firebase_test.view.SignInScreen
import com.example.firebase_test.viewmodel.AuthFactory
import com.example.firebase_test.viewmodel.AuthViewModel
import com.example.home.Module1Screen
import com.google.firebase.auth.FirebaseAuth
import com.example.firebase_test.view.LoginScreen
import com.example.firebase_test.view.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)

        val authRepository = AuthRepository()
        val authFactory = AuthFactory(authRepository)

        setContent {
            // Sử dụng MaterialTheme và Surface đúng từ Jetpack Compose
            MaterialTheme {

                //************* SIGN UP AN ACCOUNT *************
//                Surface {
//                    FirestoreExample()
//                    AuthenticationScreen()
//                }

                //************* NAVIGATION BETWEEN MODULE *************
//                    val navController = rememberNavController()
//                    NavHost(navController, startDestination = "home") {
//                        composable("home") {
//                            HomeScreen(navController)
//                        }
//                        composable("module1") {
//                            Module1Screen()
//                        }
//                    }

                //************* SIGN IN ACCOUNT *************
                MainScreen(authFactory)
                //LoginScreen()
            }
        }
    }
}


//************* SIGN IN INTERFACE ACCOUNT *************
/*@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log in",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Email or username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) "Hide" else "Show"
                    Text(
                        text = icon,
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible },
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                singleLine = true
            )

            Text(
                text = "Forgot password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 32.dp),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                textAlign = TextAlign.End
            )

            Button(
                onClick = { /* Handle login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B5C)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Log in", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? ", fontSize = 14.sp)
                Text(
                    text = "Sign up",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Navigate to sign-up screen */ }
                )
            }
        }
    }
}*/


//************** SIGN IN *****************

@Composable
fun MainScreen(authFactory: AuthFactory) {
    val authViewModel: AuthViewModel = viewModel(factory = authFactory)
    val navController = rememberNavController()

    AppNavigation(navController, authViewModel)
}

@Composable
fun AppNavigation(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController, startDestination = "signIn") {
        composable("signIn") {
            //SignInScreen(navController, authViewModel = authViewModel)
            LoginScreen(navController, authViewModel)
        }
        composable("mainScreen") {
            Module2Screen()
        }
        composable("signUp"){
            SignUpScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}



//************** NAVIGATE MODULES *****************
@Composable
fun HomeScreen(navController: NavController) {
    Button(onClick = { navController.navigate("module1") }) {
        Text("Go to Module 1")
    }
}
@Composable
fun Module2Screen() {
    Button(onClick = { /* Handle action */ }) {
        Text("Log in successfully")
    }
}



//************* SIGN UP AN ACCOUNT *************
@Composable
fun FirestoreExample() {
    var userName by remember { mutableStateOf("") }
    var usersList by remember { mutableStateOf(listOf<String>()) }
    var showToast by remember { mutableStateOf<String?>(null) }  // State để trigger Toast

    val context = LocalContext.current // Lấy context từ LocalContext
    val firestore: FirebaseFirestore = Firebase.firestore

    // Function to fetch users from Firestore
    fun fetchUsers() {
        firestore.collection("users")
            .get()
            .addOnSuccessListener { result ->
                usersList = result.documents.map { it.getString("name") ?: "" }
            }
            .addOnFailureListener {
                showToast = "Failed to fetch users!"
            }
    }

    // Function to add a user to Firestore
    fun addUserToFirestore(name: String) {
        val user = hashMapOf("name" to name)
        firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                showToast = "User added!" // Set message cho Toast
                fetchUsers() // Fetch users after adding
            }
            .addOnFailureListener {
                showToast = "Failed to add user!" // Set message cho Toast
            }
    }

    // Trigger Toast khi showToast thay đổi
    showToast?.let {
        LaunchedEffect(it) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        showToast = null // Reset trạng thái Toast sau khi hiển thị
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Enter a user name:")
        // Input for the user's name
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") }
        )

        // Button to add user to Firestore
        Button(onClick = { addUserToFirestore(userName) }) {
            Text("Add User")
        }

        // Display the list of users from Firestore
        Text("Users in Firestore:")
        usersList.forEach { user ->
            Text(user)
        }
    }

    // Fetch users when the composable is first loaded
    LaunchedEffect(Unit) {
        fetchUsers()
    }
}

//****************** AUTHENTICATION ******************
@Composable
fun AuthenticationScreen() {
    var currentScreen by remember { mutableStateOf(AuthScreen.Login) }

    // Điều hướng giữa Login và SignUp
    when (currentScreen) {
        AuthScreen.Login -> LoginScreen(onSignUpClick = { currentScreen = AuthScreen.SignUp })
        AuthScreen.SignUp -> SignUpScreen(onLoginClick = { currentScreen = AuthScreen.Login })
    }
}

enum class AuthScreen {
    Login, SignUp
}

@Composable
fun LoginScreen(onSignUpClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginUser(email, password) { errorMessage = it } // Truyền callback vào
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onSignUpClick) {
            Text("Don't have an account? Sign up")
        }

        // Hiển thị thông báo lỗi nếu có
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
    }
}

@Composable
fun SignUpScreen(onLoginClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                signUpUser(email, password, confirmPassword, name) { errorMessage = it } // Truyền callback vào
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onLoginClick) {
            Text("Already have an account? Login")
        }

        // Hiển thị thông báo lỗi nếu có
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
    }
}


// Hàm đăng ký người dùng
fun signUpUser(email: String, password: String, confirmPassword: String, name: String, onError: (String) -> Unit) {
    if (password != confirmPassword) {
        // Gọi callback khi mật khẩu không khớp
        onError("Passwords do not match")
        return
    }

    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                val userData = hashMapOf(
                    "name" to name,
                    "email" to email
                )

                // Lưu thông tin vào Firestore
                FirebaseFirestore.getInstance().collection("users")
                    .document(user?.uid ?: "")
                    .set(userData)
                    .addOnSuccessListener {
                        // Đăng ký thành công
                    }
                    .addOnFailureListener { exception ->
                        // Lỗi khi lưu thông tin Firestore
                        onError("Failed to save user data: ${exception.message}")
                    }
            } else {
                // Lỗi khi đăng ký
                val exception = task.exception
                val errorMessage = exception?.localizedMessage ?: "Unknown error"
                onError("Failed to register user: $errorMessage")
            }
        }
}


// Hàm đăng nhập người dùng
fun loginUser(email: String, password: String, onError: (String) -> Unit) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser

                // Lấy thông tin từ Firestore nếu cần
                FirebaseFirestore.getInstance().collection("users")
                    .document(user?.uid ?: "")
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val name = document.getString("name")
                            // Tiến hành điều hướng người dùng vào ứng dụng
                        }
                    }
            } else {
                // Lỗi đăng nhập
                onError("Invalid credentials")
            }
        }
}


