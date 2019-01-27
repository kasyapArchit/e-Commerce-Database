package beans;

import java.math.BigInteger;

public class Review {
    BigInteger m_product_id;
    String m_review_text;

    Review () {}
    Review (BigInteger product_id, String review_text)
    {
        this.m_product_id = product_id; this.m_review_text = review_text;
    }
}