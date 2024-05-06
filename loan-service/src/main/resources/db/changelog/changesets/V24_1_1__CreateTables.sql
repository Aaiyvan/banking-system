create table if not exists t_loans
(
    id                       uuid primary key,
    c_amount                 double precision not null,
    c_term                   integer          not null,
    c_interest_rate          double precision not null,
    c_downpayment            double precision not null,
    c_monthly_payment        double precision not null,
    c_total_accrued_interest double precision not null,
    c_customer_id            uuid             not null
);