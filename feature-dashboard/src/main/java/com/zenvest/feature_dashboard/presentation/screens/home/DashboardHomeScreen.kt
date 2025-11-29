package com.zenvest.feature_dashboard.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zenvest.core.common.extensions.formatAsINR
import com.zenvest.core.common.extensions.formatAsPercentage
import com.zenvest.core.ui.components.ZenvestLabeledProgressBar
import com.zenvest.core.ui.components.ZenvestLoading
import com.zenvest.core.ui.components.ZenvestStatCard
import com.zenvest.core.ui.theme.zenvestColors
import com.zenvest.feature_dashboard.data.models.AIPersona
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardEvent
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardHomeScreen(
    viewModel: DashboardViewModel,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        ZenvestLoading()
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Welcome Header
        item {
            Text(
                text = "Welcome back, Rajesh",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Here's your financial overview",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Stats Grid
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ZenvestStatCard(
                    title = "Monthly Income",
                    value = uiState.summary.monthlyIncome.formatAsINR(),
                    modifier = Modifier.weight(1f),
                    icon = {
                        StatIcon(
                            icon = Icons.Default.TrendingUp,
                            backgroundColor = MaterialTheme.zenvestColors.successContainer,
                            iconColor = MaterialTheme.zenvestColors.success
                        )
                    }
                )
                ZenvestStatCard(
                    title = "Expenses",
                    value = uiState.summary.plannedExpenses.formatAsINR(),
                    modifier = Modifier.weight(1f),
                    icon = {
                        StatIcon(
                            icon = Icons.Default.CreditCard,
                            backgroundColor = MaterialTheme.zenvestColors.warningContainer,
                            iconColor = MaterialTheme.zenvestColors.warning
                        )
                    }
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ZenvestStatCard(
                    title = "Emergency Fund",
                    value = (uiState.summary.emergencyFundProgress * 100).toInt().formatAsPercentage(),
                    modifier = Modifier.weight(1f),
                    icon = {
                        StatIcon(
                            icon = Icons.Default.Savings,
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            iconColor = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                ZenvestStatCard(
                    title = "Total Debt",
                    value = uiState.summary.totalDebt.formatAsINR(),
                    modifier = Modifier.weight(1f),
                    valueColor = MaterialTheme.colorScheme.error,
                    icon = {
                        StatIcon(
                            icon = Icons.Default.AccountBalance,
                            backgroundColor = MaterialTheme.colorScheme.errorContainer,
                            iconColor = MaterialTheme.colorScheme.error
                        )
                    }
                )
            }
        }

        // AI Personas Section
        item {
            AIPersonasSection(
                selectedPersona = uiState.selectedPersona,
                onPersonaSelected = { viewModel.onEvent(DashboardEvent.SelectPersona(it)) },
                aiMessage = uiState.aiMessage?.message ?: "",
                suggestedQuestions = uiState.aiMessage?.suggestedQuestions ?: emptyList()
            )
        }

        // Financial Health Section
        item {
            FinancialHealthSection(
                emergencyFundProgress = uiState.summary.emergencyFundProgress,
                savingsRate = uiState.summary.savingsRate,
                debtStatus = uiState.summary.debtStatus
            )
        }
    }
}

@Composable
private fun StatIcon(
    icon: ImageVector,
    backgroundColor: androidx.compose.ui.graphics.Color,
    iconColor: androidx.compose.ui.graphics.Color
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = iconColor
        )
    }
}

@Composable
private fun AIPersonasSection(
    selectedPersona: AIPersona,
    onPersonaSelected: (AIPersona) -> Unit,
    aiMessage: String,
    suggestedQuestions: List<String>
) {
    var chatInput by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "AI Financial Advisor",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Persona Chips
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(AIPersona.entries) { persona ->
                    FilterChip(
                        selected = selectedPersona == persona,
                        onClick = { onPersonaSelected(persona) },
                        label = {
                            Text(
                                text = when (persona) {
                                    AIPersona.BUDGET_COACH -> "Budget Coach"
                                    AIPersona.INVESTMENT_ADVISOR -> "Investment Advisor"
                                    AIPersona.DEBT_EXPERT -> "Debt Expert"
                                }
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // AI Message
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
            ) {
                Text(
                    text = aiMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Suggested Questions
            Text(
                text = "Quick questions:",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            suggestedQuestions.forEach { question ->
                TextButton(
                    onClick = { chatInput = question },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = question,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Chat Input
            OutlinedTextField(
                value = chatInput,
                onValueChange = { chatInput = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ask me anything...") },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }
    }
}

@Composable
private fun FinancialHealthSection(
    emergencyFundProgress: Float,
    savingsRate: Float,
    debtStatus: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Financial Health",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            ZenvestLabeledProgressBar(
                label = "Emergency Fund",
                progress = emergencyFundProgress,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            ZenvestLabeledProgressBar(
                label = "Savings Rate",
                progress = savingsRate,
                color = MaterialTheme.zenvestColors.success
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Debt Status",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = debtStatus,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = when (debtStatus) {
                        "On Track" -> MaterialTheme.zenvestColors.success
                        "Attention Needed" -> MaterialTheme.zenvestColors.warning
                        else -> MaterialTheme.colorScheme.error
                    }
                )
            }
        }
    }
}
