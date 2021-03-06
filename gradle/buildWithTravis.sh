if [ "$TRAVIS_TAG" == "" ]; then
  echo -e 'Testing Branch'
  ./gradlew clean test
elif [ "$TRAVIS_TAG" != "" ]; then
  echo -e 'Build Branch for tag: Tag ['$TRAVIS_TAG']'
  ./gradlew -PbintrayUser="${bintrayUser}" -PbintrayKey="${bintrayKey}" build bintrayUpload --stacktrace --info
else
  echo -e 'WARN: Should not be here ./gradlew clean'
fi