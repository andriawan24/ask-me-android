package com.andriawan.askme.ui.screens.home.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andriawan.askme.R
import com.andriawan.askme.domain.models.TopicModel
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.ui.components.CustomSpannableLinkText
import com.andriawan.askme.ui.screens.home.models.HomeUiState
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.utils.Constants.EMPTY
import com.andriawan.askme.utils.extensions.getFirstWord
import com.andriawan.askme.utils.extensions.orZero

@Composable
fun HomeScreen(
    state: HomeUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    CustomSpannableLinkText(
                        fullText = "Good Evening, ${state.user?.name.getFirstWord()}",
                        linkText = state.user?.name.getFirstWord()
                    )
                    Text(
                        text = "Points : ${state.user?.points.orZero()}",
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp),
                    model = state.user?.imageUrl.orEmpty(),
                    contentDescription = "Profile picture of ${state.user?.name.orEmpty()}",
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        item {
            Divider(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp))
        }

        item {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp),
                text = stringResource(R.string.home_today_topic_question_title),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 12.dp)) {
                items(
                    items = state.topics.orEmpty(),
                    key = { it.id }
                ) { topic ->
                    Card(
                        modifier = Modifier.padding(vertical = 14.dp, horizontal = 12.dp),
                        backgroundColor = Color(0xFFABD9FF),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 48.dp, vertical = 12.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.img_laptop),
                                contentDescription = "Image ${topic.name}",
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = topic.name,
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp),
                text = stringResource(R.string.home_popular_topic_question_title),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        items(2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                backgroundColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.img_laptop),
                        contentDescription = "Image $it"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                        text = "Topic $it",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSystemInDarkTheme()) Color.White else MaterialTheme.colors.onBackground
                        )
                    )
                    Text(
                        text = "290 Questions",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }

        item {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp),
                text = stringResource(R.string.home_most_stars_title),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        items(2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.img_laptop),
                        contentDescription = "Image $it",
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "Topic $it",
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                        Text(
                            text = "Programming",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Text(
                        text = "290 Questions",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    AskMeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            HomeScreen(
                state = HomeUiState(
                    user = UserModel(
                        name = "Naufal",
                        id = 1,
                        email = "andriawan2422@gmail.com",
                        points = 1000,
                        phoneNumber = "0808193981",
                        imageUrl = "https://ui-avatars.com/api/?name=Naufal+Fawwaz",
                        createdAt = EMPTY,
                        updatedAt = EMPTY
                    ),
                    topics = listOf(
                        TopicModel(
                            id = 1,
                            name = "Topic 1",
                            description = "This is topic 1",
                            imageURL = "https://ui-avatars.com/api/?name=Topic+1"
                        ),
                        TopicModel(
                            id = 2,
                            name = "Topic 1",
                            description = "This is topic 1",
                            imageURL = "https://ui-avatars.com/api/?name=Topic+1"
                        ),
                        TopicModel(
                            id = 3,
                            name = "Topic 1",
                            description = "This is topic 1",
                            imageURL = "https://ui-avatars.com/api/?name=Topic+1"
                        ),
                        TopicModel(
                            id = 4,
                            name = "Topic 1",
                            description = "This is topic 1",
                            imageURL = "https://ui-avatars.com/api/?name=Topic+1"
                        )
                    )
                )
            )
        }
    }
}