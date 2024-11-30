package vn.finance.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import vn.finance.theme.AppTheme

@Composable
fun OnboardingPage(modifier: Modifier = Modifier, navigateTo: () -> Unit = {}) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = modifier.background(MaterialTheme.colorScheme.primary)) {
        OnboardingBackground()
        HorizontalPager(
            state = pagerState, modifier = modifier.fillMaxSize()
        ) { page ->
            OnboardingChildPage(page = page)
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp)
        ) {
            OnBoardingIndicator(pagerState = pagerState, coroutineScope, navigateTo = navigateTo)
        }
    }
}

@Composable
private fun OnboardingBackground() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxHeight(0.3f),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp))
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
private fun OnBoardingIndicator(
    pagerState: PagerState, coroutineScope: CoroutineScope, navigateTo: () -> Unit
) {
    Column {
        TextButton(onClick = {
            coroutineScope.launch {
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    navigateTo()
                    return@launch
                }
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Next")
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun OnboardingChildPage(page: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxHeight(0.3f),
        ) {
            Text(
                if (page == 0) stringResource(R.string.welcome_to_expense_manager)
                else stringResource(R.string.are_you_ready_to_take_control_of_your_finaces),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                if (page == 0) painterResource(R.drawable.ilustracion)
                else painterResource(R.drawable.bankcard),
                if (page == 0) stringResource(R.string.welcome_illustration)
                else stringResource(R.string.welcome_bank_card_illustration),
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AppTheme {
        OnboardingPage()
    }
}