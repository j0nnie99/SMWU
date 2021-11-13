crime <- USArrests
library(tibble)
crime <- rownames_to_column(crime, var = "state")
