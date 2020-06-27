import asyncio
import concurrent.futures
import time

import aiohttp
import requests


fake_headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36'}


def download_one(url):
    resp = requests.get(url, headers=fake_headers)
    print('Read {} from {}'.format(len(resp.content), url))


async def download_one2(url):
    async with aiohttp.ClientSession() as session:
        async with session.get(url, headers=fake_headers) as resp:
            print('Read {} from {}'.format(resp.content_length, url))


async def download_all2(sites):
    tasks = [asyncio.create_task(download_one2(site)) for site in sites]
    await asyncio.gather(*tasks)


def download_all(sites):
    with concurrent.futures.ThreadPoolExecutor(max_workers=5) as executor:
        executor.map(download_one, sites)
    # for site in sites:
    #     download_one(site)


def main():
    sites = [
        'https://www.jianshu.com/p/bb389b3e398d',
        'https://www.jianshu.com/p/384c891907e0',
        'https://www.jianshu.com/p/680f740777bb',
        'https://www.jianshu.com/p/011a41acf2ef',
        'https://www.jianshu.com/p/59f37f028e66'
    ]

    start_time = time.perf_counter()
    # download_all(sites)
    asyncio.run(download_all2(sites))
    end_time = time.perf_counter()
    print('Download {} sites in {} seconds'.format(len(sites), end_time-start_time))


if __name__ == '__main__':
    main()
