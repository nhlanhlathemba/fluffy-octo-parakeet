﻿SELECT [INVOICE].INV_NUMBER, [INVOICE_PRODUCT].PROD_ID,[PRODUCT].PROD_NAME,[INVOICE_PRODUCT].QUANTITY ,  [PRODUCT].PROD_PRICE , [INVOICE].INV_DATE
FROM(([INVOICE] INNER JOIN[INVOICE_PRODUCT] ON[INVOICE].INV_NUMBER = [INVOICE_PRODUCT].INV_NUMBER)
INNER JOIN[PRODUCT] ON[INVOICE_PRODUCT].PROD_ID = [PRODUCT].PROD_ID )WHERE [INVOICE].USER_ID =7 ORDER BY[INVOICE].INV_NUMBER ASC; 