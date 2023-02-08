package com.andriawan.askme.ui.screens.questions.presenter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.askme.R
import com.andriawan.askme.ui.components.CustomTextInput
import com.andriawan.askme.ui.screens.questions.models.QuestionUiState
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.utils.DateHelper

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    uiState: QuestionUiState,
    onQueryChanged: (String) -> Unit,
    onSearchSubmitted: () -> Unit,
    focusManager: FocusManager = LocalFocusManager.current
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Text(
                text = stringResource(R.string.questions_title),
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
            )
            CustomTextInput(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                hint = "Search Questions",
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onSearchSubmitted()
                            focusManager.clearFocus()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                onValueChanged = onQueryChanged,
                value = uiState.query,
                imeAction = ImeAction.Search,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchSubmitted()
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                if (uiState.isLoading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                } else {
                    items(
                        items = uiState.questions,
                        key = { it.id }
                    ) { question ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = Color.White,
                            elevation = 0.dp
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = question.text,
                                        style = MaterialTheme.typography.body1.copy(
                                            color = Color(0xFF3C4856),
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        modifier = Modifier
                                            .weight(1F)
                                            .padding(end = 12.dp)
                                    )
                                    Text(
                                        text = DateHelper.formatDate(question.createdAt),
                                        style = MaterialTheme.typography.body2.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF6A6A6A)
                                        )
                                    )
                                }
                                Text(
                                    text = question.topic.name,
                                    style = MaterialTheme.typography.body2.copy(
                                        color = Color(0xFF6A6A6A),
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowCircleUp,
                                        contentDescription = "Upvote Total",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color(0xFF2F9B46)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = question.upVote.toString(),
                                        style = MaterialTheme.typography.body1.copy(
                                            color = Color(0xFF2F9B46),
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Icon(
                                        imageVector = Icons.Default.ArrowCircleDown,
                                        contentDescription = "Down Vote Total",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color(0xFFAB3831)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = question.downVote.toString(),
                                        style = MaterialTheme.typography.body1.copy(
                                            color = Color(0xFFAB3831),
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Icon(
                                        imageVector = Icons.Default.Comment,
                                        contentDescription = "Comments Total",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color(0xFF3C4856)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = question.answersCount.toString(),
                                        style = MaterialTheme.typography.body1.copy(
                                            color = Color(0xFF3C4856),
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = Icons.Default.Filter,
                contentDescription = "Filter Icon",
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun QuestionScreenPreview() {
    AskMeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            QuestionScreen(
                uiState = QuestionUiState(),
                onQueryChanged = {},
                onSearchSubmitted = {}
            )
        }
    }
}