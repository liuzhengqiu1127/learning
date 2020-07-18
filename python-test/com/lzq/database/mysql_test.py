
import MySQLdb


def test_pymysql():
    conn = MySQLdb.connect(
        host='localhost',
        port=3306,
        user='root',
        passwd='Qiu19871127@&',
        db='mytest'
    )

    cur = conn.cursor()
    cur.execute('''
            CREATE TABLE price (
                timestamp TIMESTAMP NOT NULL,
                BTCUSD FLOAT(8,2),
                PRIMARY KEY (timestamp)
            );
        ''')
    cur.execute('''
            INSERT INTO price VALUES(
                "2019-07-14 14:12:17",
                11234.56
            );
        ''')

    conn.commit()
    conn.close()


test_pymysql()