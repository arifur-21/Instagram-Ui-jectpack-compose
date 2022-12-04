package com.example.instagramcloneuiapp.screeens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.instagramcloneuiapp.R
import com.example.instagramcloneuiapp.model.Post
import com.example.instagramcloneuiapp.model.Stories
import com.example.instagramcloneuiapp.model.User
import com.google.accompanist.pager.*

@Composable
fun HomeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        AppToolBar()
        StoriesSection(storyList = getStories())
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .height(2.dp)
        )
        PostSection(postList = getPostData(), modifier = Modifier.fillMaxWidth())
    }

}

fun getPostData(): List<Post> = listOf(
    Post(
        userName = "Arif",
        profile = R.drawable.coffe,
        postImageList = listOf(R.drawable.coffe),
        description = "lets down the haters",
        likedBy = listOf(
            User(profile = R.drawable.coffe, userName = "arif"),
            User(profile = R.drawable.juice_bar, userName = "arif"),
            User(profile = R.drawable.coffe, userName = "arif"),
            User(profile = R.drawable.juice_bar, userName = "arif"),
            User(profile = R.drawable.coffe, userName = "arif")
        )
    ),

    Post(
        userName = "Arifur islam",
        profile = R.drawable.coffe,
        postImageList = listOf(R.drawable.coffe, R.drawable.juice_bar),
        description = "lets down the haters",
        likedBy = listOf(
            User(profile = R.drawable.juice_bar, userName = "arif"),
            User(profile = R.drawable.coffe, userName = "arif"),
            User(profile = R.drawable.juice_bar, userName = "arif"),
            User(profile = R.drawable.coffe, userName = "arif"),
            User(profile = R.drawable.coffe, userName = "arif")
        )
    ),
)

fun getStories(): List<Stories> = listOf(
    Stories(name = "Arif", profile = R.drawable.coffe),
    Stories(name = "Arif", profile = R.drawable.coffe),
    Stories(name = "Arif", profile = R.drawable.coffe),
    Stories(name = "Arif", profile = R.drawable.coffe),
    Stories(name = "Arif", profile = R.drawable.coffe),
    Stories(name = "Arif", profile = R.drawable.coffe),
    Stories(name = "Arif", profile = R.drawable.coffe),
)

@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    postList: List<Post>
) {

    LazyColumn {
        items(postList) { post ->
            PostItem(post)
        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostItem(
    post: Post,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = post.profile), contentDescription = "",
                    modifier = modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = modifier.width(5.dp))
                Text(
                    text = post.userName,
                    fontSize = 12.sp,
                    maxLines = 1,
                    modifier = modifier.width(100.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more",
                modifier = modifier
                    .size(22.dp)
                    .align(Alignment.CenterEnd)
            )
        }


        PostCarousel(post.postImageList, pagerState)

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.align(Alignment.CenterStart)) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

            if (pagerState.pageCount > 1) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Color.Green,
                    inactiveColor = Color.LightGray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .align(
                        Alignment.CenterEnd
                    )
            )
        }

        LikeSection(post.likedBy)

        val annotateString = buildAnnotatedString {
            withStyle(style = SpanStyle(Color.Black, fontWeight = FontWeight.Bold)){
                append("${post.userName} ")
            }
            append(post.description)
        }

        Text(
            text = annotateString,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun LikeSection(likedBy: List<User>) {

    if (likedBy.size > 10) {
        Text(
            text = "${likedBy.size} likes", fontWeight = FontWeight.Bold, fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    } else if (likedBy.size == 1) {
        Text(
            text = "liked by ${likedBy[0].userName} ",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
    } else {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            PostLIveViewPorfile(likedBy)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "Liked by ${likedBy[0].userName} and ${likedBy.size - 1} others",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

    }

}

@Composable
fun PostLIveViewPorfile(likedBy: List<User>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
        items(likedBy.take(4)) { likesBy ->
            Image(
                painter = painterResource(id = likesBy.profile),
                contentDescription = "Stroy profile",
                modifier = Modifier
                    .size(25.dp)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostCarousel(postImageList: List<Int>, pagerState: PagerState) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            count = postImageList.size, state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { currentPage ->
            Image(
                painter = painterResource(id = postImageList[currentPage]),
                contentDescription = "",
                modifier = Modifier
                    .size(375.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        if (pagerState.pageCount > 1) {
            NudgeCount(
                pagerState, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            )
        }

    }


}


@Composable
fun StoriesSection(modifier: Modifier = Modifier, storyList: List<Stories>) {
    LazyRow() {
        items(storyList) { story ->
            StorieItem(stories = story, modifier = Modifier)
        }
    }
}

@Composable
fun StorieItem(
    modifier: Modifier = Modifier,
    stories: Stories
) {
    Column(modifier = modifier.padding(5.dp)) {
        Image(
            painter = painterResource(id = stories.profile!!), contentDescription = "Stroy profile",
            modifier = modifier
                .size(60.dp)
                .border(
                    width = 2.dp, brush = Brush.linearGradient(
                        listOf(
                            Color("#DE0046".toColorInt()),
                            Color("#F7A34B".toColorInt())
                        )
                    ),
                    shape = CircleShape
                )
                .padding(5.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stories.name,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(50.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NudgeCount(pagerState: PagerState, modifier: Modifier) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.Black.copy(0.4f))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = "${pagerState.currentPage + 1}", color = Color.White)
        Text(text = "/", color = Color.White)
        Text(text = "${pagerState.pageCount}", color = Color.White)
    }
}


@Composable
fun AppToolBar() {

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.coffe), contentDescription = "Logo",
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .align(Alignment.TopStart)
        )
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                painter = painterResource(id = R.drawable.coffe),
                contentDescription = "post",
                modifier = Modifier.size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.coffe),
                contentDescription = "heart",
                modifier = Modifier.size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.coffe),
                contentDescription = "meseenger",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreToolBar() {
    AppToolBar()
}
