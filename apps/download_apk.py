# -*- coding: utf-8 -*-

from BeautifulSoup import BeautifulSoup
import urllib, re, os

JENKINS_URL = 'http://client-jenkins.jdb-dev.com:8080'
JOB = 'job'
PROJECT_NAME = 'Android_CI_Branch_v2.6.0_20160907'
SPLIT = '/'

def gethtml(url):
    page = urllib.urlopen(url)
    Html = page.read()
    return Html

def getUrl(html):
    soup = BeautifulSoup(html, fromEncoding='utf8')
    base_apk_url = soup.find('a', href=re.compile(r'.+jDB-release.apk'))
    all_apk_url = PROJECT_URL_PREFIX + base_apk_url['href']
    print all_apk_url
    download_filepath = os.path.join(os.getcwd(), 'jDB-release.apk')
    urllib.urlretrieve(all_apk_url, download_filepath)

if __name__ in '__main__':
    PROJECT_URL_PREFIX = JENKINS_URL + SPLIT + JOB + SPLIT + PROJECT_NAME + SPLIT
    HTML_CONTENT = gethtml(PROJECT_URL_PREFIX)
    getUrl(HTML_CONTENT)