package vn.finance.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import vn.finance.theme.AppTheme

@Composable
fun OnboardingPage(modifier: Modifier = Modifier, navigateTo: () -> Unit = {}) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.padding(16.dp)) {
        HorizontalPager(
            state = pagerState, modifier = modifier
                .fillMaxSize()
                .weight(1f)
        ) { page ->
            OnboardingChildPage(page = page)
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            OnBoardingIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp), pagerState = pagerState
            )
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            coroutineScope.launch {
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    navigateTo()
                    return@launch
                }
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }) {
            Text(stringResource(R.string.next))
        }
    }
}

@Composable
private fun OnBoardingIndicator(modifier: Modifier, pagerState: PagerState) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val lineWidth = animateFloatAsState(
                targetValue = if (pagerState.currentPage == iteration) {
                    32f
                } else {
                    16f
                }, label = "size", animationSpec = tween(300)
            )
            val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant
            Box(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
                    .width(lineWidth.value.dp)
                    .height(4.dp)
            )
        }
    }
}

@Composable
private fun OnboardingChildPage(page: Int) {
    val image = when (page) {
        0 -> painterResource(R.drawable.onboarding1)
        1 -> painterResource(R.drawable.onboarding2)
        else -> painterResource(R.drawable.onboarding3)
    }
    val contentDescription = when (page) {
        0 -> stringResource(R.string.onboarding1)
        1 -> stringResource(R.string.onboarding2)
        else -> stringResource(R.string.onboarding3)
    }
    val title = when (page) {
        0 -> stringResource(R.string.fastest_payment_in_the_world)
        1 -> stringResource(R.string.the_most_secoure_platfrom_for_customer)
        else -> stringResource(R.string.paying_for_everything_is_easy_and_convenient)
    }
    val des = when (page) {
        0 -> stringResource(R.string.integrate_multiple_payment_methoods_to_help_you_up_the_process_quickly)
        else -> stringResource(R.string.built_in_fingerprint_face_recognition_and_more_keeping_you_completely_safe)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            image,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentScale = ContentScale.FillWidth
        )
        Text(
            title,
            style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onSurface),
            textAlign = TextAlign.Center,
        )
        Text(
            des,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AppTheme {
        OnboardingPage()
    }
}