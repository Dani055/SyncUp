package com.example.syncup_android.ui.screens

import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.syncup_android.ComposeFileProvider
import com.example.syncup_android.R
import com.example.syncup_android.api.repository.ImageUploadRepository
import com.example.syncup_android.data.model.Submission
import com.example.syncup_android.permissions.CheckAndRequestCameraPermission
import com.example.syncup_android.ui.navigation.NavRoutes
import com.example.syncup_android.ui.navigation.SnackbarVisualsWithError
import com.example.syncup_android.viewmodel.ActivityDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream

@Composable
fun ActivityDetailsScreen(
    scope: CoroutineScope,
    navController: NavController,
    activityId: String?,
    activityDetailsViewModel: ActivityDetailsViewModel = viewModel(),
    snackBar: SnackbarHostState
) {
    val activityDetailsState by activityDetailsViewModel.uiState.collectAsState()

    //Load activity when component is initialized
    LaunchedEffect(Unit) {
        if (activityId == null) {
            navController.navigate(NavRoutes.PlayGame.name) {
                launchSingleTop = true;
            }
        }
        scope.launch {
            try {
                activityDetailsViewModel.loadActivityDetails(activityId!!)
            } catch (e: Exception) {
                (e).printStackTrace()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.mask_group_4_1_1),
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleLarge,
                    text = "Task details"
                )
                if (activityDetailsState.isCompleted) Icon(
                    modifier = Modifier.padding(start = 7.dp),
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = null,
                    tint = Color(0xFF289C56)
                ) else {
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 35.dp, end = 35.dp, top = 160.dp, bottom = 20.dp)
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    //Card for the task name
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                            .weight(1f)
                            .background(
                                MaterialTheme.colorScheme.onPrimary,
                                RoundedCornerShape(15.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.VideogameAsset, modifier = Modifier
                                .padding(top = 10.dp)
                                .size(32.dp), contentDescription = null
                        )
                        Text(
                            text = "Task name",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = activityDetailsState.activity?.name ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }

                    //Card for the task points
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                            .weight(1f)
                            .background(
                                MaterialTheme.colorScheme.onPrimary,
                                RoundedCornerShape(15.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.StarOutline, modifier = Modifier
                                .padding(top = 10.dp)
                                .size(32.dp), contentDescription = null
                        )
                        Text(
                            text = "Points",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = activityDetailsState.activity?.points.toString() ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                }

                //Description of task
                Text(
                    text = "Description",
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = activityDetailsState.activity?.description ?: "",
                    style = MaterialTheme.typography.titleMedium
                )

                if (activityDetailsState.isCompleted) {
                    MySubmission(submission = activityDetailsState.mySubmission ?: null)
                }
                else {
                    ImageUploadSection(
                        scope = scope,
                        activityDetailsViewModel = activityDetailsViewModel,
                        activityId = activityId!!,
                        navController = navController,
                        snackBar = snackBar
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageUploadSection(
    scope: CoroutineScope,
    activityDetailsViewModel: ActivityDetailsViewModel,
    activityId: String,
    navController: NavController,
    snackBar: SnackbarHostState
) {
    val imageUploadRepo = ImageUploadRepository()
    val haptic = LocalHapticFeedback.current

    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    //Image picker for the gallery
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )
    //Camera launcher to take a photo
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )

    val context = LocalContext.current


    val onUploadPictureClick = {
        val fileInputStream: InputStream? = context.contentResolver.openInputStream(imageUri!!);
        val mimeType = context.contentResolver.getType(imageUri!!)
        val extension: String? = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
        val file = File(context.cacheDir, "imageToUpload.$extension")
        file.createNewFile()
        //Take the content of the image
        //whether it is chosen from the gallery or taken by camera
        //and create a new file to upload to the server
        fileInputStream.use { input ->
            file.outputStream().use { output ->
                input?.copyTo(output)
            }

        }
        scope.launch {
            try {
                //Upload image and get the link to it
                val linksRes = imageUploadRepo.uploadImage(file)

                //Create a submission for the task
                val postRes = activityDetailsViewModel.postSubmission(linksRes.links[0], activityId)
                navController.navigate(NavRoutes.PlayGame.name) {
                    launchSingleTop = true
                }
                snackBar.showSnackbar("Task completed")
            } catch (e: Exception) {
                snackBar.showSnackbar(SnackbarVisualsWithError(e.message!!, true))
            }
        }
    }

    CheckAndRequestCameraPermission()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        //Button to open camera
        IconButton(colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.6f
            )
        ), modifier = Modifier
            .size(60.dp)
            .clip(
                CircleShape
            ), onClick = {
                hasImage = false;
                imageUri = null;
                val uri = ComposeFileProvider.getImageUri(context)
                imageUri = uri
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                cameraLauncher.launch(uri)
        }) {
            Icon(
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                imageVector = Icons.Outlined.AddAPhoto,
                contentDescription = "Take picture"
            )
        }
        //Button to open gallery
        IconButton(
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.6f
                )
            ), modifier = Modifier
                .size(60.dp)
                .clip(CircleShape), onClick = {
                hasImage = false;
                imageUri = null;
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                imagePicker.launch("image/*")
            }) {
            Icon(
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                imageVector = Icons.Outlined.AddPhotoAlternate,
                contentDescription = "Choose picture from device"
            )
        }
    }

    //If image has been selected display it
    if (hasImage && imageUri != null) {
        Box() {
            AsyncImage(
                model = imageUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentDescription = "Selected image",
            )

            //Button to submit
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.6f
                    )
                ), modifier = Modifier.defaultMinSize(95.dp, 27.dp),
                    onClick = { onUploadPictureClick() }) {
                    Text(text = "Click here to submit")
                }
            }

        }

    }
}

@Composable
fun MySubmission(submission: Submission?) {
    Divider(
        modifier = Modifier.padding(top = 15.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        thickness = 2.dp
    )
    //Details for submission
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            model = submission?.completedBy?.profileImageUrl,
            contentDescription = null
        )
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "${submission?.completedBy?.firstName} ${submission?.completedBy?.lastName} (me)"
        )
        Text(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp,
            text = "Just now"
        )
    }
    //Submission image
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(15.dp)),
        contentScale = ContentScale.Crop,
        model = submission?.evidenceUrl,
        contentDescription = null
    )
}

